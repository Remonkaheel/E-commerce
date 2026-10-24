/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.model;
/*    */ 
/*    */ public enum OrderStatus {
/*  4 */   PENDING("قيد الانتظار"),
/*  5 */   CONFIRMED("تم التأكيد"),
/*  6 */   PROCESSING("جاري التجهيز"),
/*  7 */   SHIPPED("تم الشحن"),
/*  8 */   DELIVERED("تم التوصيل"),
/*  9 */   CANCELLED("ملغي");
/*    */   
/*    */   private final String arabicName;
/*    */   
/*    */   OrderStatus(String arabicName) {
/* 14 */     this.arabicName = arabicName;
/*    */   }
/*    */   
/*    */   public String getArabicName() {
/* 18 */     return this.arabicName;
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\model\OrderStatus.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */