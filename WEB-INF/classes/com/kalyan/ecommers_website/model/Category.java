/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.model;
/*    */ import jakarta.persistence.Entity;
/*    */ import jakarta.persistence.GeneratedValue;
/*    */ import jakarta.persistence.GenerationType;
/*    */ import jakarta.persistence.Id;
/*    */ import jakarta.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "categories")
/*    */ public class Category {
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Long id;
/*    */   
/*    */   public Long getId() {
/* 16 */     return this.id;
/*    */   }
/*    */   private String name;
/*    */   public void setId(Long id) {
/* 20 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 24 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 28 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\model\Category.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */