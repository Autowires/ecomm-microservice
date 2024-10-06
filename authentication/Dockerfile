# Use an OpenJDK image
FROM openjdk:17-alpine

# Copy the jar file
COPY target/authentication-0.0.1-SNAPSHOT.jar authentication.jar

# Expose the port your service uses
EXPOSE 8081

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "authentication.jar"]
