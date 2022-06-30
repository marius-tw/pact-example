package com.example.demo.contract;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArray;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@PactTestFor(providerName = "CartApplication")
@Tag("contractTest")
@ExtendWith(PactConsumerTestExt.class)
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
  void getAllItems_whenItemsExist(MockServer mockServer) throws IOException {
    HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/items").execute().returnResponse();
    assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(200)));
  }

  private Map<String, String> headers() {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json; charset=utf-8");
    return headers;
  }
}
