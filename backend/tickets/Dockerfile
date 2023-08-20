FROM openjdk:19
VOLUME /tmp
COPY /target/tickets-0.0.1-SNAPSHOT.jar app.jar
COPY /scripts/wait-for-it.sh wait-for-it.sh
EXPOSE 8085
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5055","-jar","/app.jar"]