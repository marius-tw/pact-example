package com.example.demo.controller;

import com.example.demo.entity.Item;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

  @GetMapping("/items")
  List<Item> getItems(){
    return List.of(new Item("A"), new Item("B"), new Item("C"));
  }
}
