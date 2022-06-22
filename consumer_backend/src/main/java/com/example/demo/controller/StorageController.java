package com.example.demo.controller;

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
  List<String> getItemsAvailable(){
    return storageService.getItemsAvailable();
  }

  @GetMapping("/items-stored")
  List<String> getItemsStored(){
    return storageService.getItemsStored();
  }
}
