FROM maven:3.8-openjdk-18 AS build
COPY . /app
WORKDIR /app
RUN mvn clean install -DskipTests

FROM openjdk:18
COPY --from=build app/target/auth-0.0.1-SNAPSHOT.jar auth.jar
COPY src/main/resources/mytest.jks src/main/resources/mytest.jks
COPY src/main/resources/themes src/main/resources/themes
CMD ["java","-jar","/auth.jar"]