/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.model;
/*    */ 
/*    */ import jakarta.persistence.Entity;
/*    */ import jakarta.persistence.GeneratedValue;
/*    */ import jakarta.persistence.GenerationType;
/*    */ import jakarta.persistence.Id;
/*    */ import jakarta.persistence.Table;
/*    */ import java.time.LocalDateTime;
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "offers")
/*    */ public class Offer {
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Long id;
/*    */   private String imageUrlDesktop;
/*    */   private String imageUrlMobile;
/*    */   private LocalDateTime createdAt;
/*    */   
/*    */   public Long getId() {
/* 21 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Long id) {
/* 25 */     this.id = id;
/*    */   }
/*    */   
/*    */   public LocalDateTime getCreatedAt() {
/* 29 */     return this.createdAt;
/*    */   }
/*    */   
/*    */   public void setCreatedAt(LocalDateTime createdAt) {
/* 33 */     this.createdAt = createdAt;
/*    */   }
/*    */   
/*    */   public String getImageUrlDesktop() {
/* 37 */     return this.imageUrlDesktop;
/*    */   }
/*    */   
/*    */   public void setImageUrlDesktop(String imageUrlDesktop) {
/* 41 */     this.imageUrlDesktop = imageUrlDesktop;
/*    */   }
/*    */   
/*    */   public String getImageUrlMobile() {
/* 45 */     return this.imageUrlMobile;
/*    */   }
/*    */   
/*    */   public void setImageUrlMobile(String imageUrlMobile) {
/* 49 */     this.imageUrlMobile = imageUrlMobile;
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\model\Offer.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */