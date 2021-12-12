FROM openjdk:8-jdk-alpine
#ADD . /hhyboard
#WORKDIR /hhyboard
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/app.jar"]