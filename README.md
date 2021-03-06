# simple-interest-api

API built in Spring Boot with Maven to calculate simple interest.  
Test for @aplazo

## Docker

Use of docker to run the API inside a container:


### Build docker image

`docker build -t simple-interest-api .`

### Run docker container

`docker run -p 8080:8080 simple-interest-api`

## H2 Database

Local H2 Database do storage the request and response.

![Alt text](images/h2-database.png?raw=true "Health")

## Health Endpoint

Use of Spring Boot actuator to have the health endpoint.

![Alt text](images/health.png?raw=true "Health")

## Unit Test Coverage

Unit test coverage report made by Intellij Idea.

![Alt text](images/test-coverage.png?raw=true "Swagger 1")

## Swagger UI (Open API)
Use of swagger to document the API.

![Alt text](images/swagger1.png?raw=true "Swagger 1")
![Alt text](images/swagger2.png?raw=true "Swagger 2")


## Practical Example

### Request
![Alt text](images/example1.png?raw=true "Request")

### Response
![Alt text](images/example2.png?raw=true "Response")
