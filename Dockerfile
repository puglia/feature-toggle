FROM openjdk:13-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/feature-toggle-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]