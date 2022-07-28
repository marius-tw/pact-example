# PACT-example setup

This is an example for using ConsumerDrivenContractTesting with two backend-services and one
frontend.

## Local

- `cd storage-service && ./gradlew bootRun` will start the provider application on port `8080`
- `cd shopping-cart-service && ./gradlew bootRun` will start the consumer application on port `8081`

Triggering provider-endpoint:

- `curl http://localhost:8081/items-available` should return

```json
[
  {
    "name": "A"
  },
  {
    "name": "B"
  },
  {
    "name": "C"
  }
]
```

Triggering consumer-endpoint should response with a json object that contains the elements that were
sent, as long as they are the ones which are in the dummy-repsonse in the storage application:

- `curl -X POST -H "Content-Type: application/json" -d '[{"name":"A"},{"name":"B"},{"name":"C"}]' http://localhost:8080/cart`
  should return

```json
[
  {
    "name": "A"
  },
  {
    "name": "B"
  },
  {
    "name": "C"
  }
]
```

## PACT test-setup

### pre-requisites consumer:

- au.com.dius.pact.consumer:junit5:4.1.7
- au.com.dius.pact.consumer:java8:4.1.7

### pre-requisites provider:

- au.com.dius.pact.provider:junit5:4.1.7

### consumer test-setup

- for `JUnit 5` an extension annotation is required for the consumer
  test-class `@ExtendWith(PactConsumerTestExt.class)`

## PACT-broker setup

- using `https://github.com/pact-foundation/pact-broker-docker#running-with-docker-compose`

## Further Resources

- https://github.com/pact-foundation/pact-workshop-jvm-spring

