# Use OpenJDK image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Add jar (Make sure your JAR is named properly after build)
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expose port (same as your app runs on)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
