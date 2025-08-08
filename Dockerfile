
# Use OpenJDK as base
FROM openjdk:17

# Set working directory
WORKDIR /app

# Copy Spring Boot project from Myr-Pg-Backend folder
COPY Myr-Pg-Backend /app

# Give execute permission to mvnw
RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Run the application

CMD ["java", "-jar", "target/Myr-Pg-Backend-0.0.1-SNAPSHOT.jar"]
