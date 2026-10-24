/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.model;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class Cart {
/*  7 */   private Map<Long, Integer> items = new HashMap<>();
/*    */   
/*    */   public void addProduct(Long productId) {
/* 10 */     this.items.put(productId, Integer.valueOf(((Integer)this.items.getOrDefault(productId, Integer.valueOf(0))).intValue() + 1));
/*    */   }
/*    */   
/*    */   public int getTotalItems() {
/* 14 */     return this.items.values().stream().mapToInt(Integer::intValue).sum();
/*    */   }
/*    */   
/*    */   public Map<Long, Integer> getItems() {
/* 18 */     return this.items;
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\model\Cart.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */