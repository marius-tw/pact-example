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

  public List<Item> getItemsAvailable() {
    return Arrays.asList(
        new Item("A"),
        new Item("B"),
        new Item("C")
    );
  }
}
