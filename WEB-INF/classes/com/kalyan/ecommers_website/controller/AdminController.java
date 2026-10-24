/*     */ package WEB-INF.classes.com.kalyan.ecommers_website.controller;
/*     */ import com.kalyan.ecommers_website.model.Category;
/*     */ import com.kalyan.ecommers_website.model.Offer;
/*     */ import com.kalyan.ecommers_website.model.Order;
/*     */ import com.kalyan.ecommers_website.model.Product;
/*     */ import com.kalyan.ecommers_website.model.User;
/*     */ import com.kalyan.ecommers_website.repository.UserRepository;
/*     */ import com.kalyan.ecommers_website.service.CategoryService;
/*     */ import com.kalyan.ecommers_website.service.OfferService;
/*     */ import com.kalyan.ecommers_website.service.OrderService;
/*     */ import com.kalyan.ecommers_website.service.ProductService;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.math.BigDecimal;
/*     */ import java.nio.file.CopyOption;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.StandardCopyOption;
/*     */ import java.time.LocalDateTime;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.PostMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.multipart.MultipartFile;
/*     */ import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/admin"})
/*     */ public class AdminController {
/*  36 */   public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images/";
/*     */   
/*     */   @Autowired
/*     */   private ProductService productService;
/*     */   
/*     */   @Autowired
/*     */   private OrderService orderService;
/*     */   
/*     */   @Autowired
/*     */   private CategoryService categoryService;
/*     */   
/*     */   @Autowired
/*     */   private OfferService offerService;
/*     */   
/*     */   @Autowired
/*     */   private UserRepository userRepository;
/*     */   
/*     */   @GetMapping({"/products"})
/*     */   public String listProducts(Model model) {
/*  55 */     model.addAttribute("products", this.productService.getAllProducts());
/*  56 */     return "admin/products";
/*     */   }
/*     */   
/*     */   @GetMapping({"/products/add"})
/*     */   public String showAddProductForm(Model model) {
/*  61 */     model.addAttribute("product", new Product());
/*  62 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/*  63 */     return "admin/addProduct";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PostMapping({"/products/add"})
/*     */   public String addProduct(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("detailedDescription") String detailedDescription, @RequestParam("price") BigDecimal price, @RequestParam("stock") int stock, @RequestParam(value = "file1", required = false) MultipartFile file1, @RequestParam(value = "file2", required = false) MultipartFile file2, @RequestParam(value = "file3", required = false) MultipartFile file3, @RequestParam(value = "categoryId", required = false) String categoryId, @RequestParam(value = "newCategoryName", required = false) String newCategoryName, RedirectAttributes redirectAttributes) {
/*     */     try {
/*     */       Category category;
/*  81 */       Product product = new Product();
/*  82 */       product.setName(name);
/*  83 */       product.setDescription(description);
/*  84 */       product.setDetailedDescription(detailedDescription);
/*  85 */       product.setPrice(price);
/*  86 */       product.setStock(stock);
/*     */ 
/*     */ 
/*     */       
/*  90 */       if (newCategoryName != null && !newCategoryName.trim().isEmpty()) {
/*  91 */         category = this.categoryService.saveOrGetCategory(newCategoryName.trim());
/*  92 */       } else if (categoryId != null && !categoryId.isEmpty()) {
/*     */         
/*  94 */         category = (Category)this.categoryService.getCategoryById(Long.valueOf(Long.parseLong(categoryId))).orElseThrow(() -> new IllegalArgumentException("الفئة غير موجودة"));
/*     */       } else {
/*  96 */         throw new IllegalArgumentException("يجب اختيار أو إدخال فئة للمنتج");
/*     */       } 
/*  98 */       product.setCategory(category);
/*     */ 
/*     */       
/* 101 */       String uploadDir = System.getProperty("user.dir") + "/uploads/products/";
/* 102 */       File uploadFolder = new File(uploadDir);
/* 103 */       if (!uploadFolder.exists()) {
/* 104 */         uploadFolder.mkdirs();
/*     */       }
/*     */ 
/*     */       
/* 108 */       if (file1 != null && !file1.isEmpty()) {
/* 109 */         String filename1 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
/* 110 */         Path filePath1 = Paths.get(uploadDir + uploadDir, new String[0]);
/* 111 */         Files.copy(file1.getInputStream(), filePath1, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 112 */         product.setImageUrl1("/images/products/" + filename1);
/*     */       } 
/*     */       
/* 115 */       if (file2 != null && !file2.isEmpty()) {
/* 116 */         String filename2 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
/* 117 */         Path filePath2 = Paths.get(uploadDir + uploadDir, new String[0]);
/* 118 */         Files.copy(file2.getInputStream(), filePath2, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 119 */         product.setImageUrl2("/images/products/" + filename2);
/*     */       } 
/*     */       
/* 122 */       if (file3 != null && !file3.isEmpty()) {
/* 123 */         String filename3 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
/* 124 */         Path filePath3 = Paths.get(uploadDir + uploadDir, new String[0]);
/* 125 */         Files.copy(file3.getInputStream(), filePath3, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 126 */         product.setImageUrl3("/images/products/" + filename3);
/*     */       } 
/*     */ 
/*     */       
/* 130 */       this.productService.saveProduct(product);
/* 131 */       redirectAttributes.addFlashAttribute("successMessage", "✅ تم حفظ المنتج بنجاح");
/*     */     }
/* 133 */     catch (Exception e) {
/* 134 */       e.printStackTrace();
/* 135 */       redirectAttributes.addFlashAttribute("errorMessage", "❌ حدث خطأ أثناء حفظ المنتج");
/*     */     } 
/*     */     
/* 138 */     return "redirect:/admin/products";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/products/edit/{id}"})
/*     */   public String editProductForm(@PathVariable Long id, Model model) {
/* 149 */     Product product = (Product)this.productService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("المنتج غير موجود: " + id));
/*     */     
/* 151 */     model.addAttribute("product", product);
/* 152 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 153 */     return "admin/editProduct";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PostMapping({"/products/update/{id}"})
/*     */   public String updateProduct(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("detailedDescription") String detailedDescription, @RequestParam("price") BigDecimal price, @RequestParam("stock") int stock, @RequestParam(value = "file1", required = false) MultipartFile file1, @RequestParam(value = "file2", required = false) MultipartFile file2, @RequestParam(value = "file3", required = false) MultipartFile file3, @RequestParam(value = "categoryId", required = false) String categoryId, RedirectAttributes redirectAttributes) {
/*     */     try {
/* 172 */       Product product = (Product)this.productService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("المنتج غير موجود: " + id));
/*     */ 
/*     */       
/* 175 */       product.setName(name);
/* 176 */       product.setDescription(description);
/* 177 */       product.setDetailedDescription(detailedDescription);
/* 178 */       product.setPrice(price);
/* 179 */       product.setStock(stock);
/*     */ 
/*     */       
/* 182 */       if (categoryId != null && !categoryId.isEmpty()) {
/*     */         
/* 184 */         Category category = (Category)this.categoryService.getCategoryById(Long.valueOf(Long.parseLong(categoryId))).orElseThrow(() -> new IllegalArgumentException("الفئة غير موجودة"));
/* 185 */         product.setCategory(category);
/*     */       } 
/*     */ 
/*     */       
/* 189 */       String uploadDir = System.getProperty("user.dir") + "/uploads/products/";
/* 190 */       File uploadFolder = new File(uploadDir);
/* 191 */       if (!uploadFolder.exists()) {
/* 192 */         uploadFolder.mkdirs();
/*     */       }
/*     */ 
/*     */       
/* 196 */       if (file1 != null && !file1.isEmpty()) {
/* 197 */         String filename1 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
/* 198 */         Path filePath1 = Paths.get(uploadDir + uploadDir, new String[0]);
/* 199 */         Files.copy(file1.getInputStream(), filePath1, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 200 */         product.setImageUrl1("/images/products/" + filename1);
/*     */       } 
/*     */       
/* 203 */       if (file2 != null && !file2.isEmpty()) {
/* 204 */         String filename2 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
/* 205 */         Path filePath2 = Paths.get(uploadDir + uploadDir, new String[0]);
/* 206 */         Files.copy(file2.getInputStream(), filePath2, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 207 */         product.setImageUrl2("/images/products/" + filename2);
/*     */       } 
/*     */       
/* 210 */       if (file3 != null && !file3.isEmpty()) {
/* 211 */         String filename3 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
/* 212 */         Path filePath3 = Paths.get(uploadDir + uploadDir, new String[0]);
/* 213 */         Files.copy(file3.getInputStream(), filePath3, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/* 214 */         product.setImageUrl3("/images/products/" + filename3);
/*     */       } 
/*     */ 
/*     */       
/* 218 */       this.productService.saveProduct(product);
/* 219 */       redirectAttributes.addFlashAttribute("successMessage", "✅ تم تحديث المنتج بنجاح");
/*     */     }
/* 221 */     catch (Exception e) {
/* 222 */       e.printStackTrace();
/* 223 */       redirectAttributes.addFlashAttribute("errorMessage", "❌ حدث خطأ أثناء تحديث المنتج");
/*     */     } 
/*     */     
/* 226 */     return "redirect:/admin/products";
/*     */   }
/*     */ 
/*     */   
/*     */   @PostMapping({"/products/delete/{id}"})
/*     */   public String deleteProduct(@PathVariable Long id) {
/* 232 */     this.productService.deleteProductById(id);
/* 233 */     return "redirect:/admin/products?deleted";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/offers/add"})
/*     */   public String showAddOfferForm(Model model) {
/* 243 */     model.addAttribute("offer", new Offer());
/* 244 */     return "admin/addOffer";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @PostMapping({"/offers/add"})
/*     */   public String addOffer(@RequestParam("fileDesktop") MultipartFile fileDesktop, @RequestParam("fileMobile") MultipartFile fileMobile) {
/* 253 */     String uploadDir = System.getProperty("user.dir") + "/uploads";
/*     */     
/*     */     try {
/* 256 */       Path uploadPath = Paths.get(uploadDir, new String[0]);
/* 257 */       if (!Files.exists(uploadPath, new java.nio.file.LinkOption[0])) {
/* 258 */         Files.createDirectories(uploadPath, (FileAttribute<?>[])new FileAttribute[0]);
/*     */       }
/*     */ 
/*     */       
/* 262 */       if (fileDesktop.isEmpty() || fileMobile.isEmpty()) {
/* 263 */         throw new RuntimeException("One or more uploaded files are empty.");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 269 */       String desktopFileName = fileDesktop.getOriginalFilename().replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9._-]", "");
/*     */ 
/*     */ 
/*     */       
/* 273 */       String mobileFileName = fileMobile.getOriginalFilename().replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9._-]", "");
/*     */ 
/*     */       
/* 276 */       Path desktopFilePath = uploadPath.resolve(desktopFileName);
/* 277 */       Path mobileFilePath = uploadPath.resolve(mobileFileName);
/*     */ 
/*     */       
/* 280 */       Files.write(desktopFilePath, fileDesktop.getBytes(), new java.nio.file.OpenOption[0]);
/* 281 */       Files.write(mobileFilePath, fileMobile.getBytes(), new java.nio.file.OpenOption[0]);
/*     */ 
/*     */       
/* 284 */       Offer offer = new Offer();
/* 285 */       offer.setImageUrlDesktop("/images/" + desktopFileName);
/* 286 */       offer.setImageUrlMobile("/images/" + mobileFileName);
/* 287 */       offer.setCreatedAt(LocalDateTime.now());
/*     */       
/* 289 */       this.offerService.saveOffer(offer);
/*     */     }
/* 291 */     catch (IOException e) {
/* 292 */       e.printStackTrace();
/* 293 */       throw new RuntimeException("Failed to save uploaded files", e);
/*     */     } 
/*     */     
/* 296 */     return "redirect:/admin/offers";
/*     */   }
/*     */ 
/*     */   
/*     */   @GetMapping({"/offers"})
/*     */   public String listOffers(Model model) {
/* 302 */     model.addAttribute("offers", this.offerService.getAllOffers());
/* 303 */     return "admin/offers";
/*     */   }
/*     */   @GetMapping({"/offers/delete/{id}"})
/*     */   public String deleteOffer(@PathVariable("id") Long id) {
/* 307 */     this.offerService.deleteOfferById(id);
/* 308 */     return "redirect:/admin/offers";
/*     */   }
/*     */   
/*     */   @GetMapping({"/orders"})
/*     */   public String listOrders(Model model) {
/* 313 */     List<Order> orders = this.orderService.getAllOrders();
/* 314 */     model.addAttribute("orders", orders);
/* 315 */     return "admin/orders";
/*     */   }
/*     */   
/*     */   @GetMapping({"/orders/details/{id}"})
/*     */   public String orderDetails(@PathVariable Long id, Model model) {
/* 320 */     Order order = this.orderService.getOrderById(id);
/* 321 */     model.addAttribute("order", order);
/* 322 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 323 */     return "order-confirmation";
/*     */   }
/*     */   
/*     */   @GetMapping({"/orders/status/{id}/{status}"})
/*     */   public String updateOrderStatus(@PathVariable Long id, @PathVariable String status) {
/* 328 */     this.orderService.updateOrderStatus(id, status);
/* 329 */     return "redirect:/admin/orders";
/*     */   }
/*     */ 
/*     */   
/*     */   @GetMapping({"/users"})
/*     */   public String listUsers(Model model) {
/* 335 */     List<User> users = this.userRepository.findAll();
/* 336 */     model.addAttribute("users", users);
/* 337 */     return "admin/users";
/*     */   }
/*     */   
/*     */   @GetMapping({"/users/details/{id}"})
/*     */   public String userDetails(@PathVariable Long id, Model model) {
/* 342 */     User user = this.userRepository.findById(id).orElseThrow();
/* 343 */     List<Order> userOrders = this.orderService.getUserOrders(user);
/*     */     
/* 345 */     model.addAttribute("user", user);
/* 346 */     model.addAttribute("orders", userOrders);
/* 347 */     return "admin/user-details";
/*     */   }
/*     */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\controller\AdminController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */