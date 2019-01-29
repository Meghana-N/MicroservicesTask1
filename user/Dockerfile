FROM openjdk:11
ADD ./target/muzix-0.0.1-SNAPSHOT.jar /usr/src/muzix-0.0.1-SNAPSHOT.jar
EXPOSE 8095
WORKDIR usr/src
ENTRYPOINT ["java","-jar","muzix-0.0.1-SNAPSHOT.jar"]
