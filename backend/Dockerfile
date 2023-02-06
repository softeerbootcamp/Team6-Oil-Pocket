FROM openjdk:11-jdk
EXPOSE 8080
ARG JAR_FILE=./build/libs/backend-0.0.1-SNAPSHOT.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java", "-jar", "/app.war"]
