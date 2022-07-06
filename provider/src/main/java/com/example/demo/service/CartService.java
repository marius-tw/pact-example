package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.exception.ItemNotFound;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CartService {

  @Autowired
  public CartService(WebClient webClient) {
    this.webClient = webClient;
  }

  private final WebClient webClient;

  public List<Item> createCart(List<Item> cartItems) {
    List<Item> storage = fetchAvailableItemsFromStorage();
    checkItemsAvailable(storage, cartItems);
    return cartItems;
  }

  private List<Item> fetchAvailableItemsFromStorage() {
    return webClient.get()
        .uri("/items")
        .retrieve().
        bodyToMono(new ParameterizedTypeReference<List<Item>>() {
        }).block();
  }

  private void checkItemsAvailable(List<Item> storage,
      List<Item> itemsInCart) {
    itemsInCart.forEach(item -> {
      if (!storage.contains(item)) {
        throw new ItemNotFound("item not available");
      }
    });
  }
}
