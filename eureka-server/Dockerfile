FROM eclipse-temurin:17-jdk-focal
 
COPY target/*.jar app.jar

EXPOSE 8761

ENTRYPOINT [ "java", "-jar", "app.jar" ]