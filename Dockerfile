FROM openjdk:23-jdk
ARG JAR_FILE=target/mds-technical-task-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]