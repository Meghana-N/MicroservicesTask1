FROM openjdk:11
ADD ./target/Muzix-0.0.1-SNAPSHOT.jar  /home/user/src/Muzix-0.0.1-SNAPSHOT.jar
EXPOSE 8090
WORKDIR /home/user/src
ENTRYPOINT ["java","-jar","Muzix-0.0.1-SNAPSHOT.jar"]