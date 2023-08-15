FROM openjdk:19
VOLUME /tmp
COPY /target/gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5051","-jar","/app.jar"]