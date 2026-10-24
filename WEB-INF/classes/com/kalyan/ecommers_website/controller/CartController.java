/*     */ package WEB-INF.classes.com.kalyan.ecommers_website.controller;
/*     */ 
/*     */ import com.kalyan.ecommers_website.model.CartItem;
/*     */ import com.kalyan.ecommers_website.model.Category;
/*     */ import com.kalyan.ecommers_website.model.DTO.OrderRequest;
/*     */ import com.kalyan.ecommers_website.model.Order;
/*     */ import com.kalyan.ecommers_website.model.Product;
/*     */ import com.kalyan.ecommers_website.repository.UserRepository;
/*     */ import com.kalyan.ecommers_website.service.CartService;
/*     */ import com.kalyan.ecommers_website.service.CategoryService;
/*     */ import com.kalyan.ecommers_website.service.OrderService;
/*     */ import com.kalyan.ecommers_website.service.ProductService;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.ModelAttribute;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.PostMapping;
/*     */ import org.springframework.web.bind.annotation.RequestHeader;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/cart"})
/*     */ public class CartController
/*     */ {
/*     */   @Autowired
/*     */   private CartService cartService;
/*     */   @Autowired
/*     */   private ProductService productService;
/*     */   
/*     */   @PostMapping({"/add/{id}"})
/*     */   public String addToCart(@PathVariable Long id, @RequestParam(defaultValue = "1") int quantity, @RequestHeader(value = "referer", required = false) String referer) {
/*  41 */     Product product = this.productService.getProductById(id).orElseThrow();
/*  42 */     this.cartService.addProduct(product, quantity);
/*  43 */     return "redirect:" + ((referer != null) ? referer : "/"); } @Autowired
/*     */   private CategoryService categoryService; @Autowired
/*     */   private UserRepository userRepository; @Autowired
/*     */   private OrderService orderService; @PostMapping({"/buy/{id}"})
/*  47 */   public String buyNow(@PathVariable Long id) { Product product = this.productService.getProductById(id).orElseThrow();
/*  48 */     this.cartService.addProduct(product, 1);
/*  49 */     return "redirect:/cart"; }
/*     */ 
/*     */   
/*     */   @GetMapping
/*     */   public String viewCart(Model model) {
/*  54 */     List<Category> categories = this.categoryService.getAllCategories();
/*  55 */     model.addAttribute("categories", categories);
/*  56 */     model.addAttribute("activeCategoryId", null);
/*  57 */     model.addAttribute("items", this.cartService.getItems());
/*  58 */     model.addAttribute("total", this.cartService.getTotal());
/*  59 */     return "cart";
/*     */   }
/*     */ 
/*     */   
/*     */   @PostMapping({"/increase/{id}"})
/*     */   public String increaseQuantity(@PathVariable Long id, @RequestHeader(value = "referer", required = false) String referer) {
/*  65 */     this.cartService.increaseQuantity(id);
/*  66 */     return "redirect:" + ((referer != null) ? referer : "/cart");
/*     */   }
/*     */ 
/*     */   
/*     */   @PostMapping({"/decrease/{id}"})
/*     */   public String decreaseQuantity(@PathVariable Long id, @RequestHeader(value = "referer", required = false) String referer) {
/*  72 */     this.cartService.decreaseQuantity(id);
/*  73 */     return "redirect:" + ((referer != null) ? referer : "/cart");
/*     */   }
/*     */ 
/*     */   
/*     */   @PostMapping({"/remove/{id}"})
/*     */   public String removeFromCart(@PathVariable Long id, @RequestHeader(value = "referer", required = false) String referer) {
/*  79 */     this.cartService.removeProduct(id);
/*  80 */     return "redirect:" + ((referer != null) ? referer : "/cart");
/*     */   }
/*     */   
/*     */   @PostMapping({"/clear"})
/*     */   public String clearCart() {
/*  85 */     this.cartService.clearCart();
/*  86 */     return "redirect:/cart";
/*     */   }
/*     */ 
/*     */   
/*     */   @GetMapping({"/checkout"})
/*     */   public String checkout(Model model) {
/*  92 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/*  93 */     model.addAttribute("items", this.cartService.getItems());
/*  94 */     model.addAttribute("total", this.cartService.getTotal());
/*  95 */     return "checkout";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PostMapping({"/checkout/place-order"})
/*     */   public String placeOrder(@ModelAttribute OrderRequest orderRequest, RedirectAttributes redirectAttributes) {
/* 103 */     List<CartItem> cartItems = this.cartService.getItems();
/*     */     
/* 105 */     if (cartItems.isEmpty()) {
/* 106 */       redirectAttributes.addFlashAttribute("error", "السلة فارغة");
/* 107 */       return "redirect:/cart";
/*     */     } 
/*     */ 
/*     */     
/* 111 */     Order order = this.orderService.createOrder(cartItems, orderRequest);
/* 112 */     this.cartService.clearCart();
/*     */     
/* 114 */     redirectAttributes.addFlashAttribute("success", "تم إنشاء الطلب بنجاح!");
/* 115 */     return "redirect:/cart/orders/confirmation/" + order.getId();
/*     */   }
/*     */   @GetMapping({"/orders/confirmation/{id}"})
/*     */   public String orderConfirmation(@PathVariable Long id, Model model) {
/* 119 */     Order order = this.orderService.getOrderById(id);
/*     */     
/* 121 */     if (order == null) {
/* 122 */       return "redirect:/";
/*     */     }
/*     */     
/* 125 */     model.addAttribute("order", order);
/* 126 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 127 */     return "order-confirmation";
/*     */   }
/*     */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\controller\CartController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */