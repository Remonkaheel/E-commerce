/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.model;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.OrderItem;
/*    */ import com.kalyan.ecommers_website.model.OrderStatus;
/*    */ import com.kalyan.ecommers_website.model.User;
/*    */ import jakarta.persistence.CascadeType;
/*    */ import jakarta.persistence.Column;
/*    */ import jakarta.persistence.Entity;
/*    */ import jakarta.persistence.EnumType;
/*    */ import jakarta.persistence.Enumerated;
/*    */ import jakarta.persistence.FetchType;
/*    */ import jakarta.persistence.GeneratedValue;
/*    */ import jakarta.persistence.GenerationType;
/*    */ import jakarta.persistence.Id;
/*    */ import jakarta.persistence.JoinColumn;
/*    */ import jakarta.persistence.ManyToOne;
/*    */ import jakarta.persistence.OneToMany;
/*    */ import jakarta.persistence.Table;
/*    */ import java.math.BigDecimal;
/*    */ import java.time.LocalDateTime;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "orders")
/*    */ public class Order {
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Long id;
/*    */   @ManyToOne(fetch = FetchType.LAZY)
/*    */   @JoinColumn(name = "user_id", nullable = true)
/*    */   private User user;
/*    */   @OneToMany(mappedBy = "order", cascade = {CascadeType.ALL}, orphanRemoval = true)
/* 34 */   private List<OrderItem> orderItems = new ArrayList<>(); private String customerName; private String phoneNumber; private String addressLine; private String apartment; private String governorate; private String city;
/*    */   private String district;
/*    */   private String phone2;
/*    */   private BigDecimal totalAmount;
/*    */   @Enumerated(EnumType.STRING)
/* 39 */   private OrderStatus status = OrderStatus.PENDING;
/*    */   
/*    */   private LocalDateTime orderDate;
/*    */   
/*    */   @Column(length = 500)
/*    */   private String notes;
/*    */   
/*    */   public Long getId()
/*    */   {
/* 48 */     return this.id; } public void setId(Long id) {
/* 49 */     this.id = id;
/*    */   }
/* 51 */   public User getUser() { return this.user; } public void setUser(User user) {
/* 52 */     this.user = user;
/*    */   }
/* 54 */   public String getCustomerName() { return this.customerName; } public void setCustomerName(String customerName) {
/* 55 */     this.customerName = customerName;
/*    */   }
/* 57 */   public String getPhoneNumber() { return this.phoneNumber; } public void setPhoneNumber(String phoneNumber) {
/* 58 */     this.phoneNumber = phoneNumber;
/*    */   }
/* 60 */   public String getAddressLine() { return this.addressLine; } public void setAddressLine(String addressLine) {
/* 61 */     this.addressLine = addressLine;
/*    */   }
/* 63 */   public String getApartment() { return this.apartment; } public void setApartment(String apartment) {
/* 64 */     this.apartment = apartment;
/*    */   }
/* 66 */   public String getGovernorate() { return this.governorate; } public void setGovernorate(String governorate) {
/* 67 */     this.governorate = governorate;
/*    */   }
/* 69 */   public String getCity() { return this.city; } public void setCity(String city) {
/* 70 */     this.city = city;
/*    */   }
/* 72 */   public String getDistrict() { return this.district; } public void setDistrict(String district) {
/* 73 */     this.district = district;
/*    */   }
/* 75 */   public String getPhone2() { return this.phone2; } public void setPhone2(String phone2) {
/* 76 */     this.phone2 = phone2;
/*    */   }
/* 78 */   public List<OrderItem> getOrderItems() { return this.orderItems; } public void setOrderItems(List<OrderItem> orderItems) {
/* 79 */     this.orderItems = orderItems;
/*    */   }
/* 81 */   public BigDecimal getTotalAmount() { return this.totalAmount; } public void setTotalAmount(BigDecimal totalAmount) {
/* 82 */     this.totalAmount = totalAmount;
/*    */   }
/* 84 */   public OrderStatus getStatus() { return this.status; } public void setStatus(OrderStatus status) {
/* 85 */     this.status = status;
/*    */   }
/* 87 */   public LocalDateTime getOrderDate() { return this.orderDate; } public void setOrderDate(LocalDateTime orderDate) {
/* 88 */     this.orderDate = orderDate;
/*    */   }
/* 90 */   public String getNotes() { return this.notes; } public void setNotes(String notes) {
/* 91 */     this.notes = notes;
/*    */   }
/*    */   
/*    */   public void addOrderItem(OrderItem item) {
/* 95 */     this.orderItems.add(item);
/* 96 */     item.setOrder(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\model\Order.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */