package com.kalyan.ecommers_website.service;
import com.kalyan.ecommers_website.model.CartItem;
import com.kalyan.ecommers_website.model.DTO.OrderRequest;
import com.kalyan.ecommers_website.model.Order;
import com.kalyan.ecommers_website.model.OrderItem;
import com.kalyan.ecommers_website.model.OrderStatus;
import com.kalyan.ecommers_website.model.User;
import com.kalyan.ecommers_website.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;
  
  @Transactional
  public Order createOrder(List<CartItem> cartItems, OrderRequest request) {
    Order order = new Order();
    order.setCustomerName(request.getCustomerName());
    order.setPhoneNumber(request.getPhoneNumber());
    order.setAddressLine(request.getAddressLine());
    order.setApartment(request.getApartment());
    order.setGovernorate(request.getGovernorate());
    order.setCity(request.getCity());
    order.setDistrict(request.getDistrict());
    order.setPhone2(request.getPhone2());
    order.setNotes(request.getNotes());
    order.setOrderDate(LocalDateTime.now());
    order.setStatus(OrderStatus.PENDING);
    
    BigDecimal total = BigDecimal.ZERO;
    
    for (CartItem cartItem : cartItems) {
      OrderItem orderItem = new OrderItem();
      orderItem.setProductName(cartItem.getName());
      orderItem.setPrice(cartItem.getPrice());
      orderItem.setQuantity(cartItem.getQuantity());
      orderItem.setImageUrl(cartItem.getImageUrl());
      
      order.addOrderItem(orderItem);
      total = total.add(orderItem.getSubtotal());
    } 
    
    order.setTotalAmount(total);
    
    return (Order)this.orderRepository.save(order);
  }

  
  @Transactional
  public void updateOrderStatus(Long orderId, String status) {
    Order order = this.orderRepository.findById(orderId).orElseThrow();
    order.setStatus(OrderStatus.valueOf(status));
    this.orderRepository.save(order);
  }
  public List<Order> getUserOrders(User user) {
    return this.orderRepository.findByUserOrderByOrderDateDesc(user);
  }
  
  public List<Order> getAllOrders() {
    return this.orderRepository.findAll();
  }
  
  public Order getOrderById(Long id) {
    return this.orderRepository.findById(id).orElse(null);
  }
}


