/*     */ package WEB-INF.classes.com.kalyan.ecommers_website.model;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonProperty;
/*     */ import com.kalyan.ecommers_website.model.Category;
/*     */ import jakarta.persistence.Column;
/*     */ import jakarta.persistence.Entity;
/*     */ import jakarta.persistence.GeneratedValue;
/*     */ import jakarta.persistence.GenerationType;
/*     */ import jakarta.persistence.Id;
/*     */ import jakarta.persistence.JoinColumn;
/*     */ import jakarta.persistence.ManyToOne;
/*     */ import jakarta.persistence.Table;
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "products")
/*     */ public class Product {
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   private Long id;
/*     */   private String name;
/*     */   private String description;
/*     */   @JsonProperty("detailed_description")
/*     */   @Column(columnDefinition = "LONGTEXT")
/*     */   private String detailedDescription;
/*     */   private BigDecimal price;
/*     */   @Column(name = "original_price")
/*     */   private BigDecimal originalPrice;
/*     */   private int stock;
/*     */   private String imageUrl1;
/*     */   private String imageUrl2;
/*     */   private String imageUrl3;
/*     */   @ManyToOne
/*     */   @JoinColumn(name = "category_id")
/*     */   private Category category;
/*     */   
/*     */   public Long getId() {
/*  38 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  42 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  46 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  50 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  54 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  58 */     this.description = description;
/*     */   }
/*     */   public BigDecimal getPrice() {
/*  61 */     return this.price;
/*     */   } public void setPrice(BigDecimal price) {
/*  63 */     this.price = price;
/*     */     
/*  65 */     if (price != null)
/*  66 */       this.originalPrice = price.divide(new BigDecimal("0.8"), 2, 4); 
/*     */   }
/*     */   
/*     */   public BigDecimal getOriginalPrice() {
/*  70 */     return this.originalPrice; } public void setOriginalPrice(BigDecimal originalPrice) {
/*  71 */     this.originalPrice = originalPrice;
/*     */   } public int getDiscountPercentage() {
/*  73 */     if (this.originalPrice != null && this.price != null && this.originalPrice.compareTo(BigDecimal.ZERO) > 0) {
/*     */ 
/*     */       
/*  76 */       BigDecimal discount = this.originalPrice.subtract(this.price).divide(this.originalPrice, 4, 4).multiply(new BigDecimal("100"));
/*  77 */       return discount.intValue();
/*     */     } 
/*  79 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStock() {
/*  89 */     return this.stock;
/*     */   }
/*     */   
/*     */   public void setStock(int stock) {
/*  93 */     this.stock = stock;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Category getCategory() {
/*  99 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(Category category) {
/* 103 */     this.category = category;
/*     */   }
/*     */   
/*     */   public String getImageUrl1() {
/* 107 */     return this.imageUrl1;
/*     */   }
/*     */   
/*     */   public void setImageUrl1(String imageUrl1) {
/* 111 */     this.imageUrl1 = imageUrl1;
/*     */   }
/*     */   
/*     */   public String getImageUrl2() {
/* 115 */     return this.imageUrl2;
/*     */   }
/*     */   
/*     */   public void setImageUrl2(String imageUrl2) {
/* 119 */     this.imageUrl2 = imageUrl2;
/*     */   }
/*     */   
/*     */   public String getImageUrl3() {
/* 123 */     return this.imageUrl3;
/*     */   }
/*     */   
/*     */   public void setImageUrl3(String imageUrl3) {
/* 127 */     this.imageUrl3 = imageUrl3;
/*     */   }
/*     */   
/*     */   public String getDetailedDescription() {
/* 131 */     return this.detailedDescription;
/*     */   }
/*     */   
/*     */   public void setDetailedDescription(String detailedDescription) {
/* 135 */     this.detailedDescription = detailedDescription;
/*     */   }
/*     */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\model\Product.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */