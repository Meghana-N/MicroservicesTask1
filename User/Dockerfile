FROM openjdk:11
ADD ./target/User-0.0.1-SNAPSHOT.jar  /home/user/src/User-0.0.1-SNAPSHOT.jar
EXPOSE 8095
WORKDIR /home/user/src
ENTRYPOINT ["java","-jar","User-0.0.1-SNAPSHOT.jar"]