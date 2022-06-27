package com.example.demo.entity;

import org.springframework.context.annotation.EnableMBeanExport;


public class Item {

  public Item(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private String name;

}
