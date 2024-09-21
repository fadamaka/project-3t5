FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]
