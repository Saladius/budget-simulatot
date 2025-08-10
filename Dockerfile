# Étape 1 : build Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

# Étape 2 : image runtime légère
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/budget-simulator-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]