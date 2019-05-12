# Lundegaard entry test

This project is a solution of a homework given by Lundegaard. It contains a web application with a simple form for request submission. 

## Prerequisites

Either JDK8+ or docker is required for running the application.

### Running on localhost

Run the application locally using 
`./gradlew bootRun`

### Running in container

Run the application in docker container using
`./run.sh`

## Connecting to the application

The URL of running application is http://localhost:8080

### Reviewing the database

You can access the H2 database via the console http://localhost:8080/h2-console and setting the JDBC URL: `jdbc:h2:mem:testdb`

## Explanation of the solution

Application is powered by spring boot together with H2 in memory database and thymeleaf. The database is recreated with every application restart (it is just for demo purposes). The default dataset for the *Kind of Request* is defined in the `src/main/resources/data.sql`. 
Request form is localized together with error messages.
