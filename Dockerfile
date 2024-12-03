FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN ./mvnw bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/Web-1.jar app.jar
#--from=build /target/*.jar app.jar (Sugerencia de Copilot)

ENTRYPOINT ["java", "-jar", "app.jar"]