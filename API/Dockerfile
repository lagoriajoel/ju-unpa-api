FROM openjdk:11.0.16-jre

WORKDIR /app

COPY ./target/API-0.0.1-SNAPSHOT.jar /app

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "API-0.0.1-SNAPSHOT.jar"]
