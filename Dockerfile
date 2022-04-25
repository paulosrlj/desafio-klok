FROM maven AS build
# WORKDIR /library_rabbitmq
RUN mkdir -p /workspace_library
WORKDIR /workspace_library
COPY ./library_rabbitmq/pom.xml ./
COPY ./library_rabbitmq/src ./src
# RUN mvn -f ./pom.xml clean package
RUN mvn -f ./pom.xml install

WORKDIR /api_adesoes
RUN mkdir -p /workspace_api_adesoes
WORKDIR /workspace_api_adesoes
COPY ./api-adesoes/pom.xml ./
COPY ./api-adesoes/src ./src
# RUN pwd
# RUN ls
# RUN cp /workspace_library/target/library_rabbitmq-1.0.0-SNAPSHOT.jar /workspace_api_adesoes
RUN mvn -f ./pom.xml clean package

FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]