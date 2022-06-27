package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.service.StorageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

  @Autowired
  private StorageService storageService;

  @GetMapping("/items-available")
  List<Item> getItemsAvailable() {
    return storageService.getItemsAvailable();
  }

  @GetMapping("/items-stored")
  List<Item> getItemsStored() {
    return storageService.getItemsStored();
  }
}
