FROM openjdk:11-jdk
RUN apt-get install -y wget
RUN wget -q https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
RUN apt-get install ./google-chrome-stable_current_amd64.deb
RUN apt-get install -y \
    libglib2.0-0 \
    libnss3 \
    libx11-6
RUN mkdir -p /home/download
EXPOSE 8080
ARG JAR_FILE=./build/libs/backend-0.0.1-SNAPSHOT.war
COPY ${JAR_FILE} app.war
COPY chromedriver /chromedriver
ENTRYPOINT [ "java", "-jar", "/app.war" ]
