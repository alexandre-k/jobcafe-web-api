FROM gradle:jdk11

WORKDIR /

RUN mkdir /opt/webapi
ADD . /opt/webapi

WORKDIR /opt/webapi

RUN gradle build

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "/opt/webapi/build/libs/webapi-0.0.1-SNAPSHOT.jar"]
