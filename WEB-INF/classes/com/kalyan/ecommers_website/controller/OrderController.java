/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.controller;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.Order;
/*    */ import com.kalyan.ecommers_website.model.User;
/*    */ import com.kalyan.ecommers_website.repository.UserRepository;
/*    */ import com.kalyan.ecommers_website.service.CategoryService;
/*    */ import com.kalyan.ecommers_website.service.OrderService;
/*    */ import java.security.Principal;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/orders"})
/*    */ public class OrderController
/*    */ {
/*    */   @Autowired
/*    */   private OrderService orderService;
/*    */   @Autowired
/*    */   private UserRepository userRepository;
/*    */   @Autowired
/*    */   private CategoryService categoryService;
/*    */   
/*    */   @GetMapping
/*    */   public String myOrders(Model model, Principal principal) {
/* 33 */     if (principal == null) {
/* 34 */       return "redirect:/login";
/*    */     }
/*    */     
/* 37 */     User user = this.userRepository.findByEmail(principal.getName()).orElseThrow();
/* 38 */     List<Order> orders = this.orderService.getUserOrders(user);
/*    */     
/* 40 */     model.addAttribute("orders", orders);
/* 41 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 42 */     return "my-orders";
/*    */   }
/*    */   
/*    */   @GetMapping({"/details/{id}"})
/*    */   public String orderDetails(@PathVariable Long id, Model model) {
/* 47 */     Order order = this.orderService.getOrderById(id);
/*    */     
/* 49 */     if (order == null) {
/* 50 */       return "redirect:/";
/*    */     }
/*    */     
/* 53 */     model.addAttribute("order", order);
/* 54 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 55 */     return "order-confirmation";
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\controller\OrderController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */