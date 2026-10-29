package com.kalyan.ecommers_website.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "advertisements")
public class Advertisement
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String title;
  @Column(name = "image_url_desktop", nullable = false)
  private String imageUrlDesktop;
  @Column(name = "image_url_mobile", nullable = false)
  private String imageUrlMobile;
  @Column(name = "display_order")
  private Integer displayOrder;
  @Column(name = "is_active")
  private Boolean isActive = Boolean.valueOf(true);
  
  public Long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public void setTitle(String title) {
    this.title = title;
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
  
  public Integer getDisplayOrder() {
    return this.displayOrder;
  }
  
  public void setDisplayOrder(Integer displayOrder) {
    this.displayOrder = displayOrder;
  }
  
  public Boolean getIsActive() {
    return this.isActive;
  }
  
  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }
}


