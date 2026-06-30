FROM eclipse-temurin:26-jdk

WORKDIR /app

COPY target/account-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]