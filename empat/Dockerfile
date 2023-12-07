# Use the official OpenJDK base image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/empat.jar /app/app.jar

# Expose the port that your Spring Boot application will run on
EXPOSE 8086

# Command to run your application
CMD ["java", "-jar", "app.jar"]