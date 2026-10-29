package com.kalyan.ecommers_website.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String imageUrlDesktop;
  private String imageUrlMobile;
  private LocalDateTime createdAt;
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }
  
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
  
  public String getImageUrlDesktop() {
    return this.imageUrlDesktop;
  }
  
  public void setImageUrlDesktop(String imageUrlDesktop) {
    this.imageUrlDesktop = imageUrlDesktop;
  }
  
  public String getImageUrlMobile() {
    return this.imageUrlMobile;
  }
  
  public void setImageUrlMobile(String imageUrlMobile) {
    this.imageUrlMobile = imageUrlMobile;
  }
}


