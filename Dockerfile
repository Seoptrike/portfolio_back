# syntax=docker/dockerfile:1

# ---- build stage ----
FROM gradle:8.8-jdk21 AS build
WORKDIR /app

# 캐시를 위해 빌드 스크립트 먼저 복사
COPY gradlew settings.gradle build.gradle ./
COPY gradle/wrapper/ gradle/wrapper/
RUN chmod +x gradlew && ./gradlew --version

# 의존성 미리 받아서 캐시
RUN ./gradlew --no-daemon dependencies || true

# 소스 복사 후 실제 빌드
COPY src ./src
RUN ./gradlew --no-daemon clean build -x test --stacktrace --info

# ---- run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENV TZ=Asia/Seoul
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
