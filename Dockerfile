FROM openjdk:11-jdk-alpine

RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /usr/local/service