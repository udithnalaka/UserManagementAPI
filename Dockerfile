FROM openjdk:11
EXPOSE 8080
ADD target/UserManagement-0.0.1-SNAPSHOT.jar UserManagement-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/UserManagement-0.0.1-SNAPSHOT.jar"]
