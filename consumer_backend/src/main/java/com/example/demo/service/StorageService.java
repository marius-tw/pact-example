package com.example.demo.service;

import com.example.demo.entity.Item;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StorageService {

  private final WebClient webClient;

  @Autowired
  public StorageService(WebClient webClient) {
    this.webClient = webClient;
  }

  public List<Item> getItemsAvailable() {

    List<Item> storage = getItemsStored();

    List<Item> itemsInCart = fetchCartItems();

    List<Item> availableItems = filterCartItemsFromStoredItems(storage, itemsInCart);

    return availableItems;
  }

  public List<Item> getItemsStored() {
    return Arrays.asList(
        new Item("A"),
        new Item("B"),
        new Item("C"),
        new Item("D")
    );
  }

  private List<Item> fetchCartItems() {
    return webClient.get()
        .uri("/items")
        .retrieve().
        bodyToMono(new ParameterizedTypeReference<List<Item>>() {
        }).block();
  }

  private List<Item> filterCartItemsFromStoredItems(List<Item> storage,
      List<Item> itemsInCart) {
    return storage
        .stream()
        .filter(storageItem -> !itemsInCart.contains(storageItem))
        .collect(Collectors.toList());
  }
}
