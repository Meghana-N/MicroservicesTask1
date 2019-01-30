FROM openjdk:11
ADD ./target/spring-eureka-server-0.0.1-SNAPSHOT.jar  /home/user/src/spring-eureka-server-0.0.1-SNAPSHOT.jar
EXPOSE 8761
WORKDIR /home/user/src
ENTRYPOINT ["java","-jar","spring-eureka-server-0.0.1-SNAPSHOT.jar"]