package com.kalyan.ecommers_website.model;

import java.math.BigDecimal;

public class CartItem {
  private Long productId;
  private String name;
  private String imageUrl;
  private int quantity;
  private BigDecimal price;
  
  public CartItem(Long productId, String name, String imageUrl, int quantity, BigDecimal price) {
    this.productId = productId;
    this.name = name;
    this.imageUrl = imageUrl;
    this.quantity = quantity;
    this.price = price;
  }
  
  public Long getProductId() { return this.productId; }
  public String getName() { return this.name; }
  public String getImageUrl() { return this.imageUrl; }
  public int getQuantity() { return this.quantity; } public BigDecimal getPrice() {
    return this.price;
  } public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public BigDecimal getTotal() {
    return this.price.multiply(BigDecimal.valueOf(this.quantity));
  }
}


