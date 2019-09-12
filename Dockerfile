FROM gradle:jdk11

EXPOSE 8089

RUN gradle build

ENTRYPOINT ["java", "-jar", "/opt/webapi/build/libs/webapi-0.0.1-SNAPSHOT.jar"]
