package com.kalyan.ecommers_website.model;

import com.kalyan.ecommers_website.model.OrderItem;
import com.kalyan.ecommers_website.model.OrderStatus;
import com.kalyan.ecommers_website.model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = true)
  private User user;
  @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}, orphanRemoval = true)
  private List<OrderItem> orderItems = new ArrayList<>(); private String customerName; private String phoneNumber; private String addressLine; private String apartment; private String governorate; private String city;
  private String district;
  private String phone2;
  private BigDecimal totalAmount;
  @Enumerated(EnumType.STRING)
  private OrderStatus status = OrderStatus.PENDING;
  
  private LocalDateTime orderDate;
  
  @Column(length = 500)
  private String notes;
  
  public Long getId()
  {
    return this.id; } public void setId(Long id) {
    this.id = id;
  }
  public User getUser() { return this.user; } public void setUser(User user) {
    this.user = user;
  }
  public String getCustomerName() { return this.customerName; } public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
  public String getPhoneNumber() { return this.phoneNumber; } public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public String getAddressLine() { return this.addressLine; } public void setAddressLine(String addressLine) {
    this.addressLine = addressLine;
  }
  public String getApartment() { return this.apartment; } public void setApartment(String apartment) {
    this.apartment = apartment;
  }
  public String getGovernorate() { return this.governorate; } public void setGovernorate(String governorate) {
    this.governorate = governorate;
  }
  public String getCity() { return this.city; } public void setCity(String city) {
    this.city = city;
  }
  public String getDistrict() { return this.district; } public void setDistrict(String district) {
    this.district = district;
  }
  public String getPhone2() { return this.phone2; } public void setPhone2(String phone2) {
    this.phone2 = phone2;
  }
  public List<OrderItem> getOrderItems() { return this.orderItems; } public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }
  public BigDecimal getTotalAmount() { return this.totalAmount; } public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }
  public OrderStatus getStatus() { return this.status; } public void setStatus(OrderStatus status) {
    this.status = status;
  }
  public LocalDateTime getOrderDate() { return this.orderDate; } public void setOrderDate(LocalDateTime orderDate) {
    this.orderDate = orderDate;
  }
  public String getNotes() { return this.notes; } public void setNotes(String notes) {
    this.notes = notes;
  }
  
  public void addOrderItem(OrderItem item) {
    this.orderItems.add(item);
    item.setOrder(this);
  }
}


