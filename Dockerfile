FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE [PortName]
LABEL authors=""
ENTRYPOINT ["java" , "-jar", "app.jar"]
