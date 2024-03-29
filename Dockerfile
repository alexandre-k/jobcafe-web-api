FROM gradle:jdk12

WORKDIR /

RUN mkdir /opt/webapi
ADD . /opt/webapi

WORKDIR /opt/webapi

RUN gradle build

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "/opt/webapi/build/libs/jobcafe-0.0.1-snapshot.jar"]
