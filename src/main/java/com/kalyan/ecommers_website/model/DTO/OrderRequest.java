package com.kalyan.ecommers_website.model.DTO;

public class OrderRequest {
  private String customerName;
  private String phoneNumber;
  private String addressLine;
  private String apartment;
  private String governorate;
  private String city;
  private String district;
  private String phone2;
  private String notes;
  
  public String getCustomerName() {
    return this.customerName; } public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
  public String getPhoneNumber() { return this.phoneNumber; } public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  public String getAddressLine() { return this.addressLine; } public void setAddressLine(String addressLine) {
    this.addressLine = addressLine;
  }
  public String getApartment() { return this.apartment; } public void setApartment(String apartment) {
    this.apartment = apartment;
  }
  public String getGovernorate() { return this.governorate; } public void setGovernorate(String governorate) {
    this.governorate = governorate;
  }
  public String getCity() { return this.city; } public void setCity(String city) {
    this.city = city;
  }
  public String getDistrict() { return this.district; } public void setDistrict(String district) {
    this.district = district;
  }
  public String getPhone2() { return this.phone2; } public void setPhone2(String phone2) {
    this.phone2 = phone2;
  }
  public String getNotes() { return this.notes; } public void setNotes(String notes) {
    this.notes = notes;
  }
}


