# syntax=docker/dockerfile:1

# ---- build stage ----
FROM gradle:8.8-jdk21 AS build
WORKDIR /app

# 래퍼/스크립트 먼저 (캐시 최적화)
COPY gradlew settings.gradle* build.gradle* ./
COPY gradle/wrapper/ gradle/wrapper/

# 윈도우 개행/권한 이슈 방지
RUN chmod +x gradlew && sed -i 's/\r$//' gradlew && ./gradlew --no-daemon --version

# 의존성 캐시(실패해도 계속)
RUN ./gradlew --no-daemon dependencies || true

# 소스 복사 후 실제 빌드
COPY src ./src
RUN ./gradlew --no-daemon clean bootJar \
    -x test -x pmdMain -x pmdTest -x spotlessCheck \
    --stacktrace --info --warning-mode all \
 && JAR=$(ls build/libs/*.jar | grep -v plain | head -n 1) \
 && cp "$JAR" app.jar

# ---- run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/app.jar /app/app.jar
ENV TZ=Asia/Seoul
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
