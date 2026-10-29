package com.kalyan.ecommers_website.model;

public enum OrderStatus {
  PENDING("قيد الانتظار"),
  CONFIRMED("تم التأكيد"),
  PROCESSING("جاري التجهيز"),
  SHIPPED("تم الشحن"),
  DELIVERED("تم التوصيل"),
  CANCELLED("ملغي");
  
  private final String arabicName;
  
  OrderStatus(String arabicName) {
    this.arabicName = arabicName;
  }
  
  public String getArabicName() {
    return this.arabicName;
  }
}


