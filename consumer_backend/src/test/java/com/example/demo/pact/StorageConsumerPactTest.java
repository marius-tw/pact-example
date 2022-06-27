package com.example.demo.pact;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.example.demo.entity.Item;
import com.example.demo.service.StorageService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@PactTestFor(providerName = "CartApplication", hostInterface="localhost")
public class StorageConsumerPactTest {

  @Pact(consumer = "StorageApplication")
  public RequestResponsePact getAllItems(PactDslWithProvider builder) {
    return builder
        .given("items exist")
        .uponReceiving("get items in cart")
        .method("GET")
        .path("/items")
        .willRespondWith()
        .status(200)
        .headers(headers())
        .body(
            newJsonArray((rootArray) -> {
              rootArray.array((a) -> a.object((o) -> o.stringValue("name", "A")));
              rootArray.array((a) -> a.object((o) -> o.stringValue("name", "B")));
              rootArray.array((a) -> a.object((o) -> o.stringValue("name", "C")));
            }).build())
        .toPact();
  }

  @Test
  @PactTestFor(pactMethod = "getAllItems")
  void getAllItems_whenItemsExist(MockServer mockServer) {
    List<Item> expected = Arrays.asList(
        new Item ("A"),
        new Item ("B"),
        new Item ("C")
    );

    WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:8080")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();

//    List<Item> items = new StorageService(webClient);

//    assertEquals(expected, items);
  }

  private Map<String, String> headers() {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json; charset=utf-8");
    return headers;
  }
}
