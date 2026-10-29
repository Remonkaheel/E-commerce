package com.kalyan.ecommers_website.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
  private Map<Long, Integer> items = new HashMap<>();
  
  public void addProduct(Long productId) {
    this.items.put(productId, Integer.valueOf(((Integer)this.items.getOrDefault(productId, Integer.valueOf(0))).intValue() + 1));
  }
  
  public int getTotalItems() {
    return this.items.values().stream().mapToInt(Integer::intValue).sum();
  }
  
  public Map<Long, Integer> getItems() {
    return this.items;
  }
}


