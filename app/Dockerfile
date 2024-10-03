FROM eclipse-temurin:17-jdk-focal
 
COPY target/*.war app.war

ENTRYPOINT [ "java", "-jar", "app.war" ]