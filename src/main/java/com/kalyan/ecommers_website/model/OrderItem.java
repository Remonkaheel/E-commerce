package com.kalyan.ecommers_website.model;

import com.kalyan.ecommers_website.model.Order;
import com.kalyan.ecommers_website.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;
  
  public Long getId() { return this.id; } private String productName; private BigDecimal price; private int quantity; private String imageUrl; public void setId(Long id) {
    this.id = id;
  }
  public Order getOrder() { return this.order; } public void setOrder(Order order) {
    this.order = order;
  }
  public Product getProduct() { return this.product; } public void setProduct(Product product) {
    this.product = product;
  }
  public String getProductName() { return this.productName; } public void setProductName(String productName) {
    this.productName = productName;
  }
  public BigDecimal getPrice() { return this.price; } public void setPrice(BigDecimal price) {
    this.price = price;
  }
  public int getQuantity() { return this.quantity; } public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public String getImageUrl() { return this.imageUrl; } public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
  public BigDecimal getSubtotal() {
    return this.price.multiply(BigDecimal.valueOf(this.quantity));
  }
}


