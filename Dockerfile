# Use the OpenJDK 17 image as the base
FROM openjdk:17-jdk-bullseye

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle configuration files
COPY build.gradle settings.gradle /app/

# Copy the Gradle wrapper
COPY gradlew /app/
COPY gradle /app/gradle

# Copy your source code
COPY src /app/src
RUN apt-get update && apt-get install -y findutils
# Build the application using the Gradle wrapper
RUN ./gradlew build

# Expose the port your app runs on (adjust if your app uses a different port)
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "build/libs/rooshdashboard-0.0.1-SNAPSHOT.jar"]
