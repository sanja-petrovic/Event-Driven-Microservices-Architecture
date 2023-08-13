FROM openjdk:19
VOLUME /tmp
COPY /target/auth-0.0.1-SNAPSHOT.jar app.jar
COPY /scripts/wait-for-it.sh wait-for-it.sh
EXPOSE 8086
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5056","-jar","/app.jar"]
