# syntax=docker/dockerfile:1
FROM openjdk:11
EXPOSE 8080
ADD target/*.jar restful-web-services-0.0.1.jar
ENTRYPOINT ["java","-jar","/restful-web-services-0.0.1.jar"]