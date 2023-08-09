FROM openjdk:19
VOLUME /tmp
COPY /target/test-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]