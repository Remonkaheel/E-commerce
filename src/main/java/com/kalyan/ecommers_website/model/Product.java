package com.kalyan.ecommers_website.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kalyan.ecommers_website.model.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  @JsonProperty("detailed_description")
  @Column(columnDefinition = "LONGTEXT")
  private String detailedDescription;
  private BigDecimal price;
  @Column(name = "original_price")
  private BigDecimal originalPrice;
  private int stock;
  private String imageUrl1;
  private String imageUrl2;
  private String imageUrl3;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  public BigDecimal getPrice() {
    return this.price;
  } public void setPrice(BigDecimal price) {
    this.price = price;
    
    if (price != null)
      this.originalPrice = price.divide(new BigDecimal("0.8"), 2, 4); 
  }
  
  public BigDecimal getOriginalPrice() {
    return this.originalPrice; } public void setOriginalPrice(BigDecimal originalPrice) {
    this.originalPrice = originalPrice;
  } public int getDiscountPercentage() {
    if (this.originalPrice != null && this.price != null && this.originalPrice.compareTo(BigDecimal.ZERO) > 0) {

      
      BigDecimal discount = this.originalPrice.subtract(this.price).divide(this.originalPrice, 4, 4).multiply(new BigDecimal("100"));
      return discount.intValue();
    } 
    return 0;
  }






  
  public int getStock() {
    return this.stock;
  }
  
  public void setStock(int stock) {
    this.stock = stock;
  }


  
  public Category getCategory() {
    return this.category;
  }
  
  public void setCategory(Category category) {
    this.category = category;
  }
  
  public String getImageUrl1() {
    return this.imageUrl1;
  }
  
  public void setImageUrl1(String imageUrl1) {
    this.imageUrl1 = imageUrl1;
  }
  
  public String getImageUrl2() {
    return this.imageUrl2;
  }
  
  public void setImageUrl2(String imageUrl2) {
    this.imageUrl2 = imageUrl2;
  }
  
  public String getImageUrl3() {
    return this.imageUrl3;
  }
  
  public void setImageUrl3(String imageUrl3) {
    this.imageUrl3 = imageUrl3;
  }
  
  public String getDetailedDescription() {
    return this.detailedDescription;
  }
  
  public void setDetailedDescription(String detailedDescription) {
    this.detailedDescription = detailedDescription;
  }
}


