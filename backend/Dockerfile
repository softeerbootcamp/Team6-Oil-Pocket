FROM openjdk:11-jdk
EXPOSE 8080
CMD ["ls"]
ARG JAR_FILE=./backend/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
