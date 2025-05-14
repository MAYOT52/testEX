FROM gradle:8.5-jdk17 AS builder
WORKDIR /build
COPY . .
RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=builder /build/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]