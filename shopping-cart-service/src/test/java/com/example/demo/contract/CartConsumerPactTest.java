package com.example.demo.contract;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@PactTestFor(providerName = "StorageApplication")
@Tag("contractTest")
@PactFolder("contracts")
@ExtendWith(PactConsumerTestExt.class)
public class CartConsumerPactTest {

  @Pact(consumer = "CartApplication", provider = "StorageApplication")
  public RequestResponsePact getAllItems(PactDslWithProvider builder) {
    return builder
        .given("items exist")
        .uponReceiving("get items in cart")
        .method("GET")
        .path("/items-available")
        .willRespondWith()
        .status(200)
        .headers(headers())
        .body(
            newJsonArray((rootArray) -> {
              rootArray.object((o) -> o.stringValue("name", "A"));
              rootArray.object((o) -> o.stringValue("name", "B"));
              rootArray.object((o) -> o.stringValue("name", "C"));
            }).build())
        .toPact();
  }

  @Test
  @PactTestFor(pactMethod = "getAllItems")
  void getAllItems_whenItemsExist(MockServer mockServer) throws IOException {
    HttpResponse httpResponse = Request.Get(mockServer.getUrl() + "/items-available").execute().returnResponse();
    assertThat(httpResponse.getStatusLine().getStatusCode(), is(equalTo(200)));
  }

  private Map<String, String> headers() {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", "application/json");
    return headers;
  }
}
