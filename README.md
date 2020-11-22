# Memsource entry test

This project is a solution of a homework given by Memsource. 
It contains a web application with a simple form for request submission together with REST API endpoint. 

## Prerequisites

Either JDK11+ or docker is required for running the application.

### Running on localhost

Run the application locally using 
`./gradlew bootRun`

### Running in container

Run the application in docker container using
`./run.sh`

## Connecting to the application

The URL of running application is http://localhost:8080 and contains two links:
1. link to the setup form
2. link to the projects list endpoint

## Explanation of the solution

Application is powered by spring boot together with H2 in memory database. Thymeleaf is used for simple UI. 
The database is recreated with every application restart (it is just for demo purposes). 
Default dataset (Memsource account credentials) is defined in the `src/main/resources/data.sql`. 
