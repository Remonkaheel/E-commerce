/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.model;
/*    */ 
/*    */ import jakarta.persistence.Column;
/*    */ import jakarta.persistence.Entity;
/*    */ import jakarta.persistence.GeneratedValue;
/*    */ import jakarta.persistence.GenerationType;
/*    */ import jakarta.persistence.Id;
/*    */ import jakarta.persistence.Table;
/*    */ 
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "advertisements")
/*    */ public class Advertisement
/*    */ {
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Long id;
/*    */   @Column(nullable = false)
/*    */   private String title;
/*    */   @Column(name = "image_url_desktop", nullable = false)
/*    */   private String imageUrlDesktop;
/*    */   @Column(name = "image_url_mobile", nullable = false)
/*    */   private String imageUrlMobile;
/*    */   @Column(name = "display_order")
/*    */   private Integer displayOrder;
/*    */   @Column(name = "is_active")
/* 27 */   private Boolean isActive = Boolean.valueOf(true);
/*    */   
/*    */   public Long getId() {
/* 30 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Long id) {
/* 34 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getTitle() {
/* 38 */     return this.title;
/*    */   }
/*    */   
/*    */   public void setTitle(String title) {
/* 42 */     this.title = title;
/*    */   }
/*    */   
/*    */   public String getImageUrlDesktop() {
/* 46 */     return this.imageUrlDesktop;
/*    */   }
/*    */   
/*    */   public void setImageUrlDesktop(String imageUrlDesktop) {
/* 50 */     this.imageUrlDesktop = imageUrlDesktop;
/*    */   }
/*    */   
/*    */   public String getImageUrlMobile() {
/* 54 */     return this.imageUrlMobile;
/*    */   }
/*    */   
/*    */   public void setImageUrlMobile(String imageUrlMobile) {
/* 58 */     this.imageUrlMobile = imageUrlMobile;
/*    */   }
/*    */   
/*    */   public Integer getDisplayOrder() {
/* 62 */     return this.displayOrder;
/*    */   }
/*    */   
/*    */   public void setDisplayOrder(Integer displayOrder) {
/* 66 */     this.displayOrder = displayOrder;
/*    */   }
/*    */   
/*    */   public Boolean getIsActive() {
/* 70 */     return this.isActive;
/*    */   }
/*    */   
/*    */   public void setIsActive(Boolean isActive) {
/* 74 */     this.isActive = isActive;
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\model\Advertisement.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */