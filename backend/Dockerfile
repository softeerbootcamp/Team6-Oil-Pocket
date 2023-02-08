FROM ubuntu:20.04

ENV TZ=Asia/Seoul

RUN apt-get update -y
RUN apt-get install chromium-browser -y


RUN apt-get install openjdk-11-jdk -y

RUN mkdir -p /home/download

EXPOSE 8080
COPY ./build/libs/backend-0.0.1-SNAPSHOT.war app.war
COPY chromedriver /chromedriver
ENTRYPOINT [ "java", "-jar", "/app.war" ]
