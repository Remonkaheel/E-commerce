package com.kalyan.ecommers_website.controller;

import com.kalyan.ecommers_website.model.Order;
import com.kalyan.ecommers_website.model.User;
import com.kalyan.ecommers_website.repository.UserRepository;
import com.kalyan.ecommers_website.service.CategoryService;
import com.kalyan.ecommers_website.service.OrderService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping({"/orders"})
public class OrderController
{
  @Autowired
  private OrderService orderService;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CategoryService categoryService;
  
  @GetMapping
  public String myOrders(Model model, Principal principal) {
    if (principal == null) {
      return "redirect:/login";
    }
    
    User user = this.userRepository.findByEmail(principal.getName()).orElseThrow();
    List<Order> orders = this.orderService.getUserOrders(user);
    
    model.addAttribute("orders", orders);
    model.addAttribute("categories", this.categoryService.getAllCategories());
    return "my-orders";
  }
  
  @GetMapping({"/details/{id}"})
  public String orderDetails(@PathVariable Long id, Model model) {
    Order order = this.orderService.getOrderById(id);
    
    if (order == null) {
      return "redirect:/";
    }
    
    model.addAttribute("order", order);
    model.addAttribute("categories", this.categoryService.getAllCategories());
    return "order-confirmation";
  }
}


