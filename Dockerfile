FROM openjdk:11.0.13-jdk
COPY build/libs/auth-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]