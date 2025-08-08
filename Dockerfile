# Use JDK 21 base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the built JAR from the nested folder
COPY Myr-Pg-Backend/target/Myr-Pg-Backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app uses
EXPOSE 8080

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
