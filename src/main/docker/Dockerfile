FROM openjdk:21
LABEL authors="egor"

VOLUME /tmp
WORKDIR /app
ADD maven/identity-service-0.1.0-SNAPSHOT.jar /app/identityservice.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "identityservice.jar"]