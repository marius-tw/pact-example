j# PACT-example setup

This is an example for using ConsumerDrivenContractTesting with two backend-services and one frontend.

## Local

- `cd provider && ./gradlew bootRun` will start the provider application on port `8080`
- `cd consumer-backend && ./gradlew bootRun` will start the backend-consumer application on port `8081`

Triggering consumer-endpoint to fetch from provider:

- `curl http://localhost:8081/items-available`

## PACT test-setup

required dependency:

- `implementation 'au.com.dius:pact-jvm-consumer-junit5_2.12:3.6.15'`

- for `JUnit 5` an extension annotation is required for the test-class `@ExtendWith(PactConsumerTestExt.class)`

## PACT-broker setup

- using `https://github.com/pact-foundation/pact-broker-docker#running-with-docker-compose`

## Further Resources

- https://github.com/pact-foundation/pact-workshop-jvm-spring

