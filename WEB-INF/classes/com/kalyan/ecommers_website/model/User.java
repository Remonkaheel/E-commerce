/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.model;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.Order;
/*    */ import com.kalyan.ecommers_website.model.Role;
/*    */ import jakarta.persistence.CascadeType;
/*    */ import jakarta.persistence.Column;
/*    */ import jakarta.persistence.Entity;
/*    */ import jakarta.persistence.EnumType;
/*    */ import jakarta.persistence.Enumerated;
/*    */ import jakarta.persistence.FetchType;
/*    */ import jakarta.persistence.GeneratedValue;
/*    */ import jakarta.persistence.GenerationType;
/*    */ import jakarta.persistence.Id;
/*    */ import jakarta.persistence.OneToMany;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ public class User
/*    */ {
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Long id;
/*    */   private String username;
/*    */   private String password;
/*    */   private String email;
/*    */   private String phone;
/*    */   @Column(nullable = true)
/*    */   private String phoneNumber2;
/*    */   @Enumerated(EnumType.STRING)
/*    */   private Role role;
/*    */   @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
/* 35 */   private List<Order> orders = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public List<Order> getOrders() {
/* 39 */     return this.orders;
/*    */   }
/*    */   
/*    */   public void setOrders(List<Order> orders) {
/* 43 */     this.orders = orders;
/*    */   }
/*    */   public Long getId() {
/* 46 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Long id) {
/* 50 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 54 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 58 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 62 */     return this.password;
/*    */   }
/*    */   
/*    */   public void setPassword(String password) {
/* 66 */     this.password = password;
/*    */   }
/*    */   
/*    */   public String getEmail() {
/* 70 */     return this.email;
/*    */   }
/*    */   
/*    */   public void setEmail(String email) {
/* 74 */     this.email = email;
/*    */   }
/*    */   
/*    */   public String getPhone() {
/* 78 */     return this.phone;
/*    */   }
/*    */   
/*    */   public void setPhone(String phone) {
/* 82 */     this.phone = phone;
/*    */   }
/*    */   
/*    */   public Role getRole() {
/* 86 */     return this.role;
/*    */   }
/*    */   
/*    */   public void setRole(Role role) {
/* 90 */     this.role = role;
/*    */   }
/*    */   
/*    */   public String getPhoneNumber2() {
/* 94 */     return this.phoneNumber2;
/*    */   }
/*    */   
/*    */   public void setPhoneNumber2(String phoneNumber2) {
/* 98 */     this.phoneNumber2 = phoneNumber2;
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\model\User.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */