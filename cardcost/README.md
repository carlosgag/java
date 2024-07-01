# CARD COST API

## USE CASES

### - CRUD for Clearing Cost Matrix

### - CRUD for Users

### - PAYMENT COST 

#### Endpoint to retrieve payment cost of credit card given IIN (Issuer Identification Number)

***

## General description

### The API was implemented using Spring Boot 3.x with maven for builds. 

## 1. Documentation

### - Open API standard with Swagger

> Pending: Generate yaml for swagger and upload to https://app.swaggerhub.com/

### - md based documentation

> Pending: Architecture with C4 model

## 2. H2 in memory DB with hibernate JPA

## 3. Circuit breaker with resilience4j

### - Default implementation with unique


## 4. Observability & monitoring

### - Spring actuator

> In progress: adding metrics

### - Was checked the possibility of new relic and related tools but wasn't enough time for a first version 

## 5. Cache

### - Implementation of Hazelcast in memory cache to minimize external API calls

### - Due to issues this functionality was disabled

> Pending: solve issues and add caching to circuit breaker logic to retrieve cached values when is not possible to obtain remote API data.

## 6. Coding styles and quality

### - Lombok used for entities and injection (pending to modify code adding annotation to avoid constructors)

> In progress: pre-commit

## 7. Security

### - JWT based security

### - Endpoints for registration & authentication

> In progress: adding policies to allow showing of actuator, swagger API & h2

> In progress: role based authentication


## 8. High availability

###  - Dockerized app with basic configuration, uploaded image to Docker hub

> In progress: Implementing kubernetes locally with minikube.

### - Were analyzed several cloud computing providers looking for free options. Was considered out of scope to implement the whole solution with EC2 (AWS)


## 9. Testing

### - Jacoco for coverage (pending last tests for 100% coverage)

### - JUNIT 5 

### - Integration testing (in progress, only 1 basic case)

> Pending: Put tests in Gherkin format

## 10. Architecture and design

### - Layers

![CARD COST API LAYERS](/docs/cardcost.png)

> Pending: Generalize persistence, add interfaces for each layer