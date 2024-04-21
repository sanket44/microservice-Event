FROM openjdk:19
EXPOSE 8000
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} order-service.jar
ENTRYPOINT ["java","-jar","/order-service.jar"]