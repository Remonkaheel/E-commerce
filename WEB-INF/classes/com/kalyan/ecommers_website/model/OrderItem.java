/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.model;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.Order;
/*    */ import com.kalyan.ecommers_website.model.Product;
/*    */ import jakarta.persistence.Entity;
/*    */ import jakarta.persistence.FetchType;
/*    */ import jakarta.persistence.GeneratedValue;
/*    */ import jakarta.persistence.GenerationType;
/*    */ import jakarta.persistence.Id;
/*    */ import jakarta.persistence.JoinColumn;
/*    */ import jakarta.persistence.ManyToOne;
/*    */ import jakarta.persistence.Table;
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "order_items")
/*    */ public class OrderItem {
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Long id;
/*    */   @ManyToOne(fetch = FetchType.LAZY)
/*    */   @JoinColumn(name = "order_id", nullable = false)
/*    */   private Order order;
/*    */   @ManyToOne(fetch = FetchType.LAZY)
/*    */   @JoinColumn(name = "product_id")
/*    */   private Product product;
/*    */   
/* 28 */   public Long getId() { return this.id; } private String productName; private BigDecimal price; private int quantity; private String imageUrl; public void setId(Long id) {
/* 29 */     this.id = id;
/*    */   }
/* 31 */   public Order getOrder() { return this.order; } public void setOrder(Order order) {
/* 32 */     this.order = order;
/*    */   }
/* 34 */   public Product getProduct() { return this.product; } public void setProduct(Product product) {
/* 35 */     this.product = product;
/*    */   }
/* 37 */   public String getProductName() { return this.productName; } public void setProductName(String productName) {
/* 38 */     this.productName = productName;
/*    */   }
/* 40 */   public BigDecimal getPrice() { return this.price; } public void setPrice(BigDecimal price) {
/* 41 */     this.price = price;
/*    */   }
/* 43 */   public int getQuantity() { return this.quantity; } public void setQuantity(int quantity) {
/* 44 */     this.quantity = quantity;
/*    */   }
/* 46 */   public String getImageUrl() { return this.imageUrl; } public void setImageUrl(String imageUrl) {
/* 47 */     this.imageUrl = imageUrl;
/*    */   }
/*    */   public BigDecimal getSubtotal() {
/* 50 */     return this.price.multiply(BigDecimal.valueOf(this.quantity));
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\model\OrderItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */