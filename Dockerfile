# ---- Build stage ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Gradle 캐시를 위해 설정/의존 먼저 복사
COPY gradlew gradle/ settings.gradle build.gradle ./
# (멀티모듈/버전 카탈로그 쓰면 여기도 추가 COPY)

# 의존성만 먼저 받기 (캐시)
RUN ./gradlew --no-daemon dependencies || true

# 실제 소스
COPY . .

# 테스트 생략 빌드 (배포용)
RUN ./gradlew --no-daemon assemble -x test

# ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# 프로파일/포트는 환경변수로 제어
ENV JAVA_OPTS="-XX:+UseContainerSupport"
EXPOSE 10000
CMD ["sh","-c","java $JAVA_OPTS -jar app.jar"]
