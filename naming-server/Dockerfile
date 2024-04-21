FROM openjdk:19
EXPOSE 8761
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} naming-server.jar
ENTRYPOINT ["java","-jar","/naming-server.jar"]