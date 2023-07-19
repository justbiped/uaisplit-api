FROM openjdk:11-jdk-slim
ARG JAR_FILE=application/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]