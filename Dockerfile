FROM gradle:7.6-jdk23 AS builder

WORKDIR /app

COPY . .

RUN ./gradlew build -x test

FROM openjdk:23-jdk-slim

WORKDIR /usr/app

COPY --from=builder /app/build/libs/geo-surf-spring-boot.jar /usr/app/geo-surf-spring-boot.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "geo-surf-spring-boot.jar"]
