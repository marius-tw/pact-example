# Local

- `cd provider && ./gradlew bootRun` will start the provider application on port `8080`
- `cd consumer-backend && ./gradlew bootRun` will start the backend-consumer application on port `8081`

Triggering consumer-endpoint to fetch from provider:

- `curl http://localhost:8081/items-available`

# PACT-broker setup

- using `https://github.com/pact-foundation/pact-broker-docker#running-with-docker-compose`
