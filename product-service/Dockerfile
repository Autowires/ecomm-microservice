# Use an OpenJDK image
FROM openjdk:17-alpine

# Copy the jar file
COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar

# Expose the port your service uses
EXPOSE 8081

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "product-service.jar"]
