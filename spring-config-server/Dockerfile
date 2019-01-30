FROM openjdk:11
ADD ./target/spring-config-server-0.0.1-SNAPSHOT.jar  /home/user/src/spring-config-server-0.0.1-SNAPSHOT.jar
EXPOSE 8888
WORKDIR /home/user/src
ENTRYPOINT ["java","-jar","spring-config-server-0.0.1-SNAPSHOT.jar"]