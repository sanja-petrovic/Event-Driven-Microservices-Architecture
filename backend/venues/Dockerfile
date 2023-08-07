FROM openjdk:19
VOLUME /tmp
COPY /target/venues-0.0.1-SNAPSHOT.jar app.jar
COPY /scripts/wait-for-it.sh wait-for-it.sh
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]