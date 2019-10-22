# Getting Started

### github repo - clone
https://github.com/udithnalaka/UserManagementAPI.git

### build, test, package app
mvn clean install

### run the app
java -jar /target/UserManagement-0.0.1-SNAPSHOT.jar

### swagger ui
localhost:8080/swagger-ui.html#/user-controller/

### dockerizing the app
docker image is created and added to docker hub
* to pull -> docker pull udithnalaka/user-management-service
* to run -> docker run -p 9090:8080 udithnalaka/user-management-service
* swagger doc -> localhost:9090/swagger-ui.html#/user-controller


#### notes
* I have tested the emails using Mailtrap.io. a screenshot of tested emails is attached in the screenshots folder.

* I have written integration tests, as I feel it's more relevant for API testing. Unit tests can also be written
 where there is business logic to test mainly in the service layer. 
 
* a simple performance test can be run locally by using Postman runner. can simulate the number of requests
to be sent to an endpoint with an interval. 
 
 Creator,
 Udith