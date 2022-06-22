package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StorageService {

  private final WebClient webClient;

  public StorageService() {
    this.webClient = WebClient.builder()
        .baseUrl("http://localhost:8080")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  public List<String> getItemsAvailable() {

    List<String> storage = getItemsStored();

    List<String> itemsInCart = fetchCartItems();

    List<String> availableItems = filterCartItemsFromStoredItems(storage, itemsInCart);

    return availableItems;
  }

  public List<String> getItemsStored() {
    return List.of("A","B","C","D");
  }

  private List<String> fetchCartItems() {
    return webClient.get()
        .uri("/items")
        .retrieve().
        bodyToMono(new ParameterizedTypeReference<List<String>>() {
        }).block();
  }

  private List<String> filterCartItemsFromStoredItems(List<String> storage, List<String> itemsInCart) {
    return storage
        .stream()
        .filter(storageItem -> {
          assert itemsInCart != null;
          return !itemsInCart.contains(storageItem);
        }).collect(
            Collectors.toList());
  }
}
