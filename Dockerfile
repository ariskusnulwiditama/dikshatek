# Dockerfile
FROM openjdk:17-jdk-slim
# Set the working directory
WORKDIR /app

# Copy the application JAR file to the container
COPY target/lawrecontest-1.0.0.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]