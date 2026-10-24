/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.service;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.CartItem;
/*    */ import com.kalyan.ecommers_website.model.Product;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.annotation.ScopedProxyMode;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.web.context.annotation.SessionScope;
/*    */ 
/*    */ 
/*    */ @Service
/*    */ @SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
/*    */ public class CartService
/*    */ {
/* 17 */   private List<CartItem> items = new ArrayList<>();
/*    */   
/*    */   public void addProduct(Product product, int quantity) {
/* 20 */     for (CartItem item : this.items) {
/* 21 */       if (item.getProductId().equals(product.getId())) {
/* 22 */         item.setQuantity(item.getQuantity() + quantity);
/*    */         return;
/*    */       } 
/*    */     } 
/* 26 */     this.items.add(new CartItem(product
/* 27 */           .getId(), product
/* 28 */           .getName(), product
/* 29 */           .getImageUrl1(), quantity, product
/*    */           
/* 31 */           .getPrice()));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateQuantity(Long productId, int quantity) {
/* 36 */     for (CartItem item : this.items) {
/* 37 */       if (item.getProductId().equals(productId)) {
/* 38 */         if (quantity > 0) {
/* 39 */           item.setQuantity(quantity);
/*    */         } else {
/* 41 */           this.items.remove(item);
/*    */         } 
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void increaseQuantity(Long productId) {
/* 49 */     for (CartItem item : this.items) {
/* 50 */       if (item.getProductId().equals(productId)) {
/* 51 */         item.setQuantity(item.getQuantity() + 1);
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void decreaseQuantity(Long productId) {
/* 58 */     for (CartItem item : this.items) {
/* 59 */       if (item.getProductId().equals(productId)) {
/* 60 */         if (item.getQuantity() > 1) {
/* 61 */           item.setQuantity(item.getQuantity() - 1);
/*    */         } else {
/* 63 */           this.items.remove(item);
/*    */         } 
/*    */         return;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void removeProduct(Long productId) {
/* 71 */     this.items.removeIf(item -> item.getProductId().equals(productId));
/*    */   }
/*    */   
/*    */   public List<CartItem> getItems() {
/* 75 */     return this.items;
/*    */   }
/*    */   
/*    */   public BigDecimal getTotal() {
/* 79 */     return this.items.stream()
/* 80 */       .map(CartItem::getTotal)
/* 81 */       .reduce(BigDecimal.ZERO, BigDecimal::add);
/*    */   }
/*    */   
/*    */   public void clearCart() {
/* 85 */     this.items.clear();
/*    */   }
/*    */   
/*    */   public int getItemCount() {
/* 89 */     return this.items.stream().mapToInt(CartItem::getQuantity).sum();
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\service\CartService.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */