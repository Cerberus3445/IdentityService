FROM openjdk:21
LABEL authors="egor"

VOLUME /tmp
WORKDIR /app
ADD maven/identity-service-0.0.1-SNAPSHOT.jar /app/identityservice.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "identityservice.jar"]