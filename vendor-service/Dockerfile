FROM openjdk:19
EXPOSE 8001
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} vendor-service.jar
ENTRYPOINT ["java","-jar","/vendor-service.jar"]