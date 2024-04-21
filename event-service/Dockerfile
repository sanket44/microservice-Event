FROM openjdk:19
EXPOSE 8005
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} event-service.jar
ENTRYPOINT ["java","-jar","/event-service.jar"]