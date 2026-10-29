package com.kalyan.ecommers_website.service;

import com.kalyan.ecommers_website.model.CartItem;
import com.kalyan.ecommers_website.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;


@Service
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService
{
  private List<CartItem> items = new ArrayList<>();
  
  public void addProduct(Product product, int quantity) {
    for (CartItem item : this.items) {
      if (item.getProductId().equals(product.getId())) {
        item.setQuantity(item.getQuantity() + quantity);
        return;
      } 
    } 
    this.items.add(new CartItem(product
          .getId(), product
          .getName(), product
          .getImageUrl1(), quantity, product
          
          .getPrice()));
  }

  
  public void updateQuantity(Long productId, int quantity) {
    for (CartItem item : this.items) {
      if (item.getProductId().equals(productId)) {
        if (quantity > 0) {
          item.setQuantity(quantity);
        } else {
          this.items.remove(item);
        } 
        return;
      } 
    } 
  }
  
  public void increaseQuantity(Long productId) {
    for (CartItem item : this.items) {
      if (item.getProductId().equals(productId)) {
        item.setQuantity(item.getQuantity() + 1);
        return;
      } 
    } 
  }
  
  public void decreaseQuantity(Long productId) {
    for (CartItem item : this.items) {
      if (item.getProductId().equals(productId)) {
        if (item.getQuantity() > 1) {
          item.setQuantity(item.getQuantity() - 1);
        } else {
          this.items.remove(item);
        } 
        return;
      } 
    } 
  }
  
  public void removeProduct(Long productId) {
    this.items.removeIf(item -> item.getProductId().equals(productId));
  }
  
  public List<CartItem> getItems() {
    return this.items;
  }
  
  public BigDecimal getTotal() {
    return this.items.stream()
      .map(CartItem::getTotal)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
  
  public void clearCart() {
    this.items.clear();
  }
  
  public int getItemCount() {
    return this.items.stream().mapToInt(CartItem::getQuantity).sum();
  }
}


