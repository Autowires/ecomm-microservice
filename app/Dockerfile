FROM eclipse-temurin:17-jdk-focal
 
COPY target/*.war app.war

EXPOSE 8761

ENTRYPOINT [ "java", "-jar", "app.war" ]