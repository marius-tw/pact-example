package com.example.demo.entity;

import java.util.Objects;

public class Item {

  public Item(){}

  public Item(String name) {
    this.name = name;
  }

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return name.equals(item.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
