FROM openjdk:8-jdk-alpine
MAINTAINER burakgungor.com
VOLUME /tmp
EXPOSE 8080
ADD target/airline-booking-0.0.1.jar airline-booking.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/airline-booking.jar"]
