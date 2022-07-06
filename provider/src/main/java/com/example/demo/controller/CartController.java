package com.example.demo.controller;

import com.example.demo.entity.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.demo.service.CartService;

@RestController
public class CartController {

  @Autowired
  private CartService cartService;

  @PostMapping("/cart")
  public List<Item> createCart(@RequestBody List<Item> cartItems) {
    return cartService.createCart(cartItems);
  }
}
