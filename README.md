# transaction-service

Microservice to manage transactions

This component exposes a REST HTTP API.

## Getting started

### Clone Repository

Use Git or checkout with SVN using the following web URL:

```
git clone https://github.com/ajdafonte/transaction-service.git
```

### Build and run component

Execute the following gradle task:

```
./gradlew build
```

### Running the tests

Execute the following gradle task:

```
./gradlew test
```

### Running the component

```
./gradlew bootRun
```

### Import into IDE

This is a Gradle project (build.gradle). Here are some instructions on how to import a Gradle
project in one of the most popular IDEs:

- IntelliJ - https://www.jetbrains.com/help/idea/gradle.html#gradle_import

## Technical Decisions

In this section will be described some details about technical decisions made.

### Stack

- Java 21
- Spring Boot 3.4
- Gradle 8.11.1

### Dependencies

- Annotations: Lombok
- Testing libs: JUnit 5, Mockito, Hamcrest, RestAssured

### Available endpoints

- `POST /api/v1/transactions` - Allows to create a new Transaction.

    - Request body:
        - terminalId
        - terminalThreatScore
        - amount - With specific value and currency
        - cardNumber

    - Response:
        - 201 CREATED with an object representing the operation outcome

Here's an example of how to make a request for this endpoint:

```
curl -X POST http://localhost:8080/api/v1/transactions \
     -H "Content-Type: application/json" \
     -d '{"terminalId":"e3211be6-d0cc-4718-905d-ab933cc91ecb","terminalThreatScore":50,"amount":{"currency":"DKK","value":50},"cardNumber":"4100000099998888"}'
```

And an example of a response body:

```
{"status":"success","message":"Transaction created","fraudScore":0}
```

### General remarks & assumptions

- Component will run on port 8080
- Hexagonal architecture was the architectural pattern used to structure this component
- Development was done following TDD and Clean Code best practices
- About testing only unit tests and one acceptance test were defined
- The following two rules were defined
    - Unknown terminal
    - Amount too large given the thread score for this terminal
- No persistence storage was defined (ie. no transaction created is stored) but the skeleton for its
  support was defined
- The following aspects were not considered during the development of this component and could be
  considered in future next stages:
    - Add a persistence storage (ie. DB)
    - OpenAPI specification
    - Pagination support (when transactions need to be retrieved)
    - API authentication/authorization
    - Add Docker support
    - Consider using circuit breaker pattern
    - Extend support for different amount limits (for example, per country) related
      with the threat score validation
    - Consider encryption for sensitive data
    - Limit API Rate and Request Frequency
