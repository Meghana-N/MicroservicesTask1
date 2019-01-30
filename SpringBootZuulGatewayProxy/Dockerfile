FROM openjdk:11
ADD ./target/SpringBootZuulGatewayProxy-0.0.1-SNAPSHOT.jar  /home/user/src/SpringBootZuulGatewayProxy-0.0.1-SNAPSHOT.jar
EXPOSE 8093
WORKDIR /home/user/src
ENTRYPOINT ["java","-jar","SpringBootZuulGatewayProxy-0.0.1-SNAPSHOT.jar"]