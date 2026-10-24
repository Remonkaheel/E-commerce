/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.model;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class CartItem {
/*    */   private Long productId;
/*    */   private String name;
/*    */   private String imageUrl;
/*    */   private int quantity;
/*    */   private BigDecimal price;
/*    */   
/*    */   public CartItem(Long productId, String name, String imageUrl, int quantity, BigDecimal price) {
/* 13 */     this.productId = productId;
/* 14 */     this.name = name;
/* 15 */     this.imageUrl = imageUrl;
/* 16 */     this.quantity = quantity;
/* 17 */     this.price = price;
/*    */   }
/*    */   
/* 20 */   public Long getProductId() { return this.productId; }
/* 21 */   public String getName() { return this.name; }
/* 22 */   public String getImageUrl() { return this.imageUrl; }
/* 23 */   public int getQuantity() { return this.quantity; } public BigDecimal getPrice() {
/* 24 */     return this.price;
/*    */   } public void setQuantity(int quantity) {
/* 26 */     this.quantity = quantity;
/*    */   }
/*    */   public BigDecimal getTotal() {
/* 29 */     return this.price.multiply(BigDecimal.valueOf(this.quantity));
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\model\CartItem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */