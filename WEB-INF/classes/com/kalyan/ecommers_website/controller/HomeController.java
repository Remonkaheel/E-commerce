/*     */ package WEB-INF.classes.com.kalyan.ecommers_website.controller;
/*     */ 
/*     */ import com.kalyan.ecommers_website.model.Advertisement;
/*     */ import com.kalyan.ecommers_website.model.Category;
/*     */ import com.kalyan.ecommers_website.model.Product;
/*     */ import com.kalyan.ecommers_website.repository.ProductRepository;
/*     */ import com.kalyan.ecommers_website.service.AdvertisementService;
/*     */ import com.kalyan.ecommers_website.service.CartService;
/*     */ import com.kalyan.ecommers_website.service.CategoryService;
/*     */ import com.kalyan.ecommers_website.service.OfferService;
/*     */ import com.kalyan.ecommers_website.service.ProductService;
/*     */ import jakarta.servlet.http.HttpSession;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Controller
/*     */ public class HomeController
/*     */ {
/*     */   @Autowired
/*     */   private OfferService offerService;
/*     */   @Autowired
/*     */   private ProductService productService;
/*     */   @Autowired
/*     */   private CategoryService categoryService;
/*     */   @Autowired
/*     */   private AdvertisementService advertisementService;
/*     */   @Autowired
/*     */   private ProductRepository productRepository;
/*     */   @Autowired
/*     */   private CartService cartService;
/*     */   
/*     */   @GetMapping({"/"})
/*     */   public String home(HttpSession session, Model model) {
/*  45 */     model.addAttribute("offers", this.offerService.getAllOffers());
/*  46 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/*  47 */     model.addAttribute("activeCategoryId", null);
/*  48 */     session.setAttribute("init", Boolean.valueOf(true));
/*  49 */     model.addAttribute("activePage", "home");
/*  50 */     Map<Category, Product> categoryProducts = new HashMap<>();
/*  51 */     List<Category> categories = this.categoryService.getAllCategories();
/*  52 */     for (Category category : categories) {
/*  53 */       List<Product> products = this.productService.getProductsByCategory(category.getId());
/*  54 */       if (!products.isEmpty())
/*     */       {
/*  56 */         categoryProducts.put(category, products.get(0));
/*     */       }
/*     */     } 
/*  59 */     model.addAttribute("categoryProducts", categoryProducts);
/*  60 */     List<Advertisement> advertisements = this.advertisementService.getActiveAdvertisements();
/*  61 */     model.addAttribute("advertisements", advertisements);
/*     */     
/*  63 */     return "home";
/*     */   }
/*     */   @GetMapping({"/contact"})
/*     */   public String contact(Model model) {
/*  67 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/*  68 */     model.addAttribute("activeCategoryId", null);
/*  69 */     model.addAttribute("activePage", "contact");
/*  70 */     return "contact";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/product/{id}"})
/*     */   public String productDetails(@PathVariable Long id, Model model) {
/*  78 */     Product product = this.productService.getProductById(id).orElse(null);
/*     */     
/*  80 */     if (product == null) {
/*  81 */       return "redirect:/";
/*     */     }
/*     */ 
/*     */     
/*  85 */     List<Product> relatedProducts = this.productRepository.findByCategoryId(product
/*  86 */         .getCategory().getId());
/*     */     
/*  88 */     model.addAttribute("relatedProducts", relatedProducts);
/*     */ 
/*     */ 
/*     */     
/*  92 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/*     */ 
/*     */     
/*  95 */     model.addAttribute("product", product);
/*  96 */     model.addAttribute("relatedProducts", relatedProducts);
/*     */ 
/*     */ 
/*     */     
/* 100 */     model.addAttribute("activePage", "product");
/* 101 */     model.addAttribute("activeCategoryId", product.getCategory().getId());
/*     */     
/* 103 */     addCartInfoToModel(model);
/*     */     
/* 105 */     model.addAttribute("product", product);
/* 106 */     model.addAttribute("relatedProducts", relatedProducts);
/* 107 */     model.addAttribute("activePage", "product");
/*     */     
/* 109 */     return "product-details";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addCartInfoToModel(Model model) {
/*     */     try {
/* 117 */       if (this.cartService != null) {
/* 118 */         model.addAttribute("cartItemCount", Integer.valueOf(this.cartService.getItemCount()));
/* 119 */         model.addAttribute("cartTotal", this.cartService.getTotal());
/* 120 */         model.addAttribute("cartItems", this.cartService.getItems());
/*     */       } else {
/* 122 */         model.addAttribute("cartItemCount", Integer.valueOf(0));
/* 123 */         model.addAttribute("cartTotal", "0");
/* 124 */         model.addAttribute("cartItems", List.of());
/*     */       } 
/* 126 */     } catch (Exception e) {
/* 127 */       model.addAttribute("cartItemCount", Integer.valueOf(0));
/* 128 */       model.addAttribute("cartTotal", "0");
/* 129 */       model.addAttribute("cartItems", List.of());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\controller\HomeController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */