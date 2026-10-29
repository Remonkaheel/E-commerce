package com.kalyan.ecommers_website.controller;

import com.kalyan.ecommers_website.model.CartItem;
import com.kalyan.ecommers_website.model.Category;
import com.kalyan.ecommers_website.model.DTO.OrderRequest;
import com.kalyan.ecommers_website.model.Order;
import com.kalyan.ecommers_website.model.Product;
import com.kalyan.ecommers_website.repository.UserRepository;
import com.kalyan.ecommers_website.service.CartService;
import com.kalyan.ecommers_website.service.CategoryService;
import com.kalyan.ecommers_website.service.OrderService;
import com.kalyan.ecommers_website.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;





@Controller
@RequestMapping({"/cart"})
public class CartController
{
  @Autowired
  private CartService cartService;
  @Autowired
  private ProductService productService;
  
  @PostMapping({"/add/{id}"})
  public String addToCart(@PathVariable Long id, @RequestParam(defaultValue = "1") int quantity, @RequestHeader(value = "referer", required = false) String referer) {
    Product product = this.productService.getProductById(id).orElseThrow();
    this.cartService.addProduct(product, quantity);
    return "redirect:" + ((referer != null) ? referer : "/"); } @Autowired
  private CategoryService categoryService; @Autowired
  private UserRepository userRepository; @Autowired
  private OrderService orderService; @PostMapping({"/buy/{id}"})
  public String buyNow(@PathVariable Long id) { Product product = this.productService.getProductById(id).orElseThrow();
    this.cartService.addProduct(product, 1);
    return "redirect:/cart"; }

  
  @GetMapping
  public String viewCart(Model model) {
    List<Category> categories = this.categoryService.getAllCategories();
    model.addAttribute("categories", categories);
    model.addAttribute("activeCategoryId", null);
    model.addAttribute("items", this.cartService.getItems());
    model.addAttribute("total", this.cartService.getTotal());
    return "cart";
  }

  
  @PostMapping({"/increase/{id}"})
  public String increaseQuantity(@PathVariable Long id, @RequestHeader(value = "referer", required = false) String referer) {
    this.cartService.increaseQuantity(id);
    return "redirect:" + ((referer != null) ? referer : "/cart");
  }

  
  @PostMapping({"/decrease/{id}"})
  public String decreaseQuantity(@PathVariable Long id, @RequestHeader(value = "referer", required = false) String referer) {
    this.cartService.decreaseQuantity(id);
    return "redirect:" + ((referer != null) ? referer : "/cart");
  }

  
  @PostMapping({"/remove/{id}"})
  public String removeFromCart(@PathVariable Long id, @RequestHeader(value = "referer", required = false) String referer) {
    this.cartService.removeProduct(id);
    return "redirect:" + ((referer != null) ? referer : "/cart");
  }
  
  @PostMapping({"/clear"})
  public String clearCart() {
    this.cartService.clearCart();
    return "redirect:/cart";
  }

  
  @GetMapping({"/checkout"})
  public String checkout(Model model) {
    model.addAttribute("categories", this.categoryService.getAllCategories());
    model.addAttribute("items", this.cartService.getItems());
    model.addAttribute("total", this.cartService.getTotal());
    return "checkout";
  }



  
  @PostMapping({"/checkout/place-order"})
  public String placeOrder(@ModelAttribute OrderRequest orderRequest, RedirectAttributes redirectAttributes) {
    List<CartItem> cartItems = this.cartService.getItems();
    
    if (cartItems.isEmpty()) {
      redirectAttributes.addFlashAttribute("error", "السلة فارغة");
      return "redirect:/cart";
    } 

    
    Order order = this.orderService.createOrder(cartItems, orderRequest);
    this.cartService.clearCart();
    
    redirectAttributes.addFlashAttribute("success", "تم إنشاء الطلب بنجاح!");
    return "redirect:/cart/orders/confirmation/" + order.getId();
  }
  @GetMapping({"/orders/confirmation/{id}"})
  public String orderConfirmation(@PathVariable Long id, Model model) {
    Order order = this.orderService.getOrderById(id);
    
    if (order == null) {
      return "redirect:/";
    }
    
    model.addAttribute("order", order);
    model.addAttribute("categories", this.categoryService.getAllCategories());
    return "order-confirmation";
  }
}


