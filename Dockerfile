FROM openjdk:8-jdk-alpine
ADD . /hhyboard
WORKDIR /hhyboard
RUN ./gradlew clean build
ARG JAR_FILE=./build/libs/hhyboard-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${PROFILE}", "/app.jar"]