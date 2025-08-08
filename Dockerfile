
# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/Myr-Pg-Backend/target/Myr-Pg-Backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]