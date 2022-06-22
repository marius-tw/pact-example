package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

  @GetMapping("/items")
  List<String> getItems(){
    return List.of("A", "B", "C");
  }
}
