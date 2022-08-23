# PACT-example setup

This is an example for using ConsumerDrivenContractTesting between two backend-services and one
frontend.

## Start local

- `cd storage-service && ./gradlew bootRun` will start the provider application on port `8081`
- `cd shopping-cart-service && ./gradlew bootRun` will start the consumer application on port `8082`
- `cd storage-ui && yarn start` will start the ui on port `3000`

Triggering provider-endpoint of the `storage-service`:

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

Triggering consumer-endpoint of the `shopping-cart-service` should respond with a json object that
contains the elements that were sent, as long as they are the ones which are in the dummy-response
of the `storage-service` (from above, cf. as well `StorageService`):

- `curl -X POST -H "Content-Type: application/json" -d '[{"name":"A"},{"name":"B"},{"name":"C"}]' http://localhost:8082/cart`
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

Whereas

`curl -X POST -H "Content-Type: application/json" -d '[{"name":"D"}]' http://localhost:8082/cart`

should return an Internal Server Error on the consumer side, and you should see something
like `ItemNotFound: item D not available` in the logs of the provider-service.

## PACT test-setup

### pre-requisites consumer:

- au.com.dius.pact.consumer:junit5:4.1.7
- au.com.dius.pact.consumer:java8:4.1.7

### pre-requisites provider:

- au.com.dius.pact.provider:junit5:4.1.7

### consumer test-setup

- for `JUnit 5` an extension annotation is required for the consumer
  test-class `@ExtendWith(PactConsumerTestExt.class)`

## run PACT tests locally

First we need to run the contract-test at the consumer-application to create the contract-json

`pushd shopping-cart-service && ./gradlew contractTest && popd` will run the consumer contract-tests
and create a contract-json

After the contract-json has been created we copy the contract to the provider-application's
test-resources directory

`cp shopping-cart-service/contracts/CartApplication-StorageApplication.json storage-service/contracts/`

With those contracts we can run the provider's contract tests against the created contract-json with

`pushd storage-service && ./gradlew contractTest && popd` will start the provider contract-tests

## PACT-broker setup

- using `https://github.com/pact-foundation/pact-broker-docker#running-with-docker-compose`

## Further Resources

- https://github.com/pact-foundation/pact-workshop-jvm-spring

