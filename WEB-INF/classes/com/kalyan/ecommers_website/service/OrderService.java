/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.service;
/*    */ import com.kalyan.ecommers_website.model.CartItem;
/*    */ import com.kalyan.ecommers_website.model.DTO.OrderRequest;
/*    */ import com.kalyan.ecommers_website.model.Order;
/*    */ import com.kalyan.ecommers_website.model.OrderItem;
/*    */ import com.kalyan.ecommers_website.model.OrderStatus;
/*    */ import com.kalyan.ecommers_website.model.User;
/*    */ import com.kalyan.ecommers_website.repository.OrderRepository;
/*    */ import java.math.BigDecimal;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ public class OrderService {
/*    */   @Autowired
/*    */   private OrderRepository orderRepository;
/*    */   
/*    */   @Transactional
/*    */   public Order createOrder(List<CartItem> cartItems, OrderRequest request) {
/* 21 */     Order order = new Order();
/* 22 */     order.setCustomerName(request.getCustomerName());
/* 23 */     order.setPhoneNumber(request.getPhoneNumber());
/* 24 */     order.setAddressLine(request.getAddressLine());
/* 25 */     order.setApartment(request.getApartment());
/* 26 */     order.setGovernorate(request.getGovernorate());
/* 27 */     order.setCity(request.getCity());
/* 28 */     order.setDistrict(request.getDistrict());
/* 29 */     order.setPhone2(request.getPhone2());
/* 30 */     order.setNotes(request.getNotes());
/* 31 */     order.setOrderDate(LocalDateTime.now());
/* 32 */     order.setStatus(OrderStatus.PENDING);
/*    */     
/* 34 */     BigDecimal total = BigDecimal.ZERO;
/*    */     
/* 36 */     for (CartItem cartItem : cartItems) {
/* 37 */       OrderItem orderItem = new OrderItem();
/* 38 */       orderItem.setProductName(cartItem.getName());
/* 39 */       orderItem.setPrice(cartItem.getPrice());
/* 40 */       orderItem.setQuantity(cartItem.getQuantity());
/* 41 */       orderItem.setImageUrl(cartItem.getImageUrl());
/*    */       
/* 43 */       order.addOrderItem(orderItem);
/* 44 */       total = total.add(orderItem.getSubtotal());
/*    */     } 
/*    */     
/* 47 */     order.setTotalAmount(total);
/*    */     
/* 49 */     return (Order)this.orderRepository.save(order);
/*    */   }
/*    */ 
/*    */   
/*    */   @Transactional
/*    */   public void updateOrderStatus(Long orderId, String status) {
/* 55 */     Order order = this.orderRepository.findById(orderId).orElseThrow();
/* 56 */     order.setStatus(OrderStatus.valueOf(status));
/* 57 */     this.orderRepository.save(order);
/*    */   }
/*    */   public List<Order> getUserOrders(User user) {
/* 60 */     return this.orderRepository.findByUserOrderByOrderDateDesc(user);
/*    */   }
/*    */   
/*    */   public List<Order> getAllOrders() {
/* 64 */     return this.orderRepository.findAll();
/*    */   }
/*    */   
/*    */   public Order getOrderById(Long id) {
/* 68 */     return this.orderRepository.findById(id).orElse(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\service\OrderService.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */