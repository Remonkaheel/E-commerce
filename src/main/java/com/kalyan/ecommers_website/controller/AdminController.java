package com.kalyan.ecommers_website.controller;
import com.kalyan.ecommers_website.model.Category;
import com.kalyan.ecommers_website.model.Offer;
import com.kalyan.ecommers_website.model.Order;
import com.kalyan.ecommers_website.model.Product;
import com.kalyan.ecommers_website.model.User;
import com.kalyan.ecommers_website.repository.UserRepository;
import com.kalyan.ecommers_website.service.CategoryService;
import com.kalyan.ecommers_website.service.OfferService;
import com.kalyan.ecommers_website.service.OrderService;
import com.kalyan.ecommers_website.service.ProductService;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/admin"})
public class AdminController {
  public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images/";
  
  @Autowired
  private ProductService productService;
  
  @Autowired
  private OrderService orderService;
  
  @Autowired
  private CategoryService categoryService;
  
  @Autowired
  private OfferService offerService;
  
  @Autowired
  private UserRepository userRepository;
  
  @GetMapping({"/products"})
  public String listProducts(Model model) {
    model.addAttribute("products", this.productService.getAllProducts());
    return "admin/products";
  }
  
  @GetMapping({"/products/add"})
  public String showAddProductForm(Model model) {
    model.addAttribute("product", new Product());
    model.addAttribute("categories", this.categoryService.getAllCategories());
    return "admin/addProduct";
  }











  
  @PostMapping({"/products/add"})
  public String addProduct(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("detailedDescription") String detailedDescription, @RequestParam("price") BigDecimal price, @RequestParam("stock") int stock, @RequestParam(value = "file1", required = false) MultipartFile file1, @RequestParam(value = "file2", required = false) MultipartFile file2, @RequestParam(value = "file3", required = false) MultipartFile file3, @RequestParam(value = "categoryId", required = false) String categoryId, @RequestParam(value = "newCategoryName", required = false) String newCategoryName, RedirectAttributes redirectAttributes) {
    try {
      Category category;
      Product product = new Product();
      product.setName(name);
      product.setDescription(description);
      product.setDetailedDescription(detailedDescription);
      product.setPrice(price);
      product.setStock(stock);


      
      if (newCategoryName != null && !newCategoryName.trim().isEmpty()) {
        category = this.categoryService.saveOrGetCategory(newCategoryName.trim());
      } else if (categoryId != null && !categoryId.isEmpty()) {
        
        category = (Category)this.categoryService.getCategoryById(Long.valueOf(Long.parseLong(categoryId))).orElseThrow(() -> new IllegalArgumentException("الفئة غير موجودة"));
      } else {
        throw new IllegalArgumentException("يجب اختيار أو إدخال فئة للمنتج");
      } 
      product.setCategory(category);

      
      String uploadDir = System.getProperty("user.dir") + "/uploads/products/";
      File uploadFolder = new File(uploadDir);
      if (!uploadFolder.exists()) {
        uploadFolder.mkdirs();
      }

      
      if (file1 != null && !file1.isEmpty()) {
        String filename1 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
        Path filePath1 = Paths.get(uploadDir + uploadDir, new String[0]);
        Files.copy(file1.getInputStream(), filePath1, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
        product.setImageUrl1("/images/products/" + filename1);
      } 
      
      if (file2 != null && !file2.isEmpty()) {
        String filename2 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
        Path filePath2 = Paths.get(uploadDir + uploadDir, new String[0]);
        Files.copy(file2.getInputStream(), filePath2, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
        product.setImageUrl2("/images/products/" + filename2);
      } 
      
      if (file3 != null && !file3.isEmpty()) {
        String filename3 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
        Path filePath3 = Paths.get(uploadDir + uploadDir, new String[0]);
        Files.copy(file3.getInputStream(), filePath3, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
        product.setImageUrl3("/images/products/" + filename3);
      } 

      
      this.productService.saveProduct(product);
      redirectAttributes.addFlashAttribute("successMessage", "✅ تم حفظ المنتج بنجاح");
    }
    catch (Exception e) {
      e.printStackTrace();
      redirectAttributes.addFlashAttribute("errorMessage", "❌ حدث خطأ أثناء حفظ المنتج");
    } 
    
    return "redirect:/admin/products";
  }






  
  @GetMapping({"/products/edit/{id}"})
  public String editProductForm(@PathVariable Long id, Model model) {
    Product product = (Product)this.productService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("المنتج غير موجود: " + id));
    
    model.addAttribute("product", product);
    model.addAttribute("categories", this.categoryService.getAllCategories());
    return "admin/editProduct";
  }













  
  @PostMapping({"/products/update/{id}"})
  public String updateProduct(@PathVariable Long id, @RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("detailedDescription") String detailedDescription, @RequestParam("price") BigDecimal price, @RequestParam("stock") int stock, @RequestParam(value = "file1", required = false) MultipartFile file1, @RequestParam(value = "file2", required = false) MultipartFile file2, @RequestParam(value = "file3", required = false) MultipartFile file3, @RequestParam(value = "categoryId", required = false) String categoryId, RedirectAttributes redirectAttributes) {
    try {
      Product product = (Product)this.productService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("المنتج غير موجود: " + id));

      
      product.setName(name);
      product.setDescription(description);
      product.setDetailedDescription(detailedDescription);
      product.setPrice(price);
      product.setStock(stock);

      
      if (categoryId != null && !categoryId.isEmpty()) {
        
        Category category = (Category)this.categoryService.getCategoryById(Long.valueOf(Long.parseLong(categoryId))).orElseThrow(() -> new IllegalArgumentException("الفئة غير موجودة"));
        product.setCategory(category);
      } 

      
      String uploadDir = System.getProperty("user.dir") + "/uploads/products/";
      File uploadFolder = new File(uploadDir);
      if (!uploadFolder.exists()) {
        uploadFolder.mkdirs();
      }

      
      if (file1 != null && !file1.isEmpty()) {
        String filename1 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
        Path filePath1 = Paths.get(uploadDir + uploadDir, new String[0]);
        Files.copy(file1.getInputStream(), filePath1, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
        product.setImageUrl1("/images/products/" + filename1);
      } 
      
      if (file2 != null && !file2.isEmpty()) {
        String filename2 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
        Path filePath2 = Paths.get(uploadDir + uploadDir, new String[0]);
        Files.copy(file2.getInputStream(), filePath2, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
        product.setImageUrl2("/images/products/" + filename2);
      } 
      
      if (file3 != null && !file3.isEmpty()) {
        String filename3 = "" + UUID.randomUUID() + "_" + UUID.randomUUID();
        Path filePath3 = Paths.get(uploadDir + uploadDir, new String[0]);
        Files.copy(file3.getInputStream(), filePath3, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
        product.setImageUrl3("/images/products/" + filename3);
      } 

      
      this.productService.saveProduct(product);
      redirectAttributes.addFlashAttribute("successMessage", "✅ تم تحديث المنتج بنجاح");
    }
    catch (Exception e) {
      e.printStackTrace();
      redirectAttributes.addFlashAttribute("errorMessage", "❌ حدث خطأ أثناء تحديث المنتج");
    } 
    
    return "redirect:/admin/products";
  }

  
  @PostMapping({"/products/delete/{id}"})
  public String deleteProduct(@PathVariable Long id) {
    this.productService.deleteProductById(id);
    return "redirect:/admin/products?deleted";
  }





  
  @GetMapping({"/offers/add"})
  public String showAddOfferForm(Model model) {
    model.addAttribute("offer", new Offer());
    return "admin/addOffer";
  }




  
  @PostMapping({"/offers/add"})
  public String addOffer(@RequestParam("fileDesktop") MultipartFile fileDesktop, @RequestParam("fileMobile") MultipartFile fileMobile) {
    String uploadDir = System.getProperty("user.dir") + "/uploads";
    
    try {
      Path uploadPath = Paths.get(uploadDir, new String[0]);
      if (!Files.exists(uploadPath, new java.nio.file.LinkOption[0])) {
        Files.createDirectories(uploadPath, (FileAttribute<?>[])new FileAttribute[0]);
      }

      
      if (fileDesktop.isEmpty() || fileMobile.isEmpty()) {
        throw new RuntimeException("One or more uploaded files are empty.");
      }



      
      String desktopFileName = fileDesktop.getOriginalFilename().replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9._-]", "");


      
      String mobileFileName = fileMobile.getOriginalFilename().replaceAll("\\s+", "_").replaceAll("[^a-zA-Z0-9._-]", "");

      
      Path desktopFilePath = uploadPath.resolve(desktopFileName);
      Path mobileFilePath = uploadPath.resolve(mobileFileName);

      
      Files.write(desktopFilePath, fileDesktop.getBytes(), new java.nio.file.OpenOption[0]);
      Files.write(mobileFilePath, fileMobile.getBytes(), new java.nio.file.OpenOption[0]);

      
      Offer offer = new Offer();
      offer.setImageUrlDesktop("/images/" + desktopFileName);
      offer.setImageUrlMobile("/images/" + mobileFileName);
      offer.setCreatedAt(LocalDateTime.now());
      
      this.offerService.saveOffer(offer);
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to save uploaded files", e);
    } 
    
    return "redirect:/admin/offers";
  }

  
  @GetMapping({"/offers"})
  public String listOffers(Model model) {
    model.addAttribute("offers", this.offerService.getAllOffers());
    return "admin/offers";
  }
  @GetMapping({"/offers/delete/{id}"})
  public String deleteOffer(@PathVariable("id") Long id) {
    this.offerService.deleteOfferById(id);
    return "redirect:/admin/offers";
  }
  
  @GetMapping({"/orders"})
  public String listOrders(Model model) {
    List<Order> orders = this.orderService.getAllOrders();
    model.addAttribute("orders", orders);
    return "admin/orders";
  }
  
  @GetMapping({"/orders/details/{id}"})
  public String orderDetails(@PathVariable Long id, Model model) {
    Order order = this.orderService.getOrderById(id);
    model.addAttribute("order", order);
    model.addAttribute("categories", this.categoryService.getAllCategories());
    return "order-confirmation";
  }
  
  @GetMapping({"/orders/status/{id}/{status}"})
  public String updateOrderStatus(@PathVariable Long id, @PathVariable String status) {
    this.orderService.updateOrderStatus(id, status);
    return "redirect:/admin/orders";
  }

  
  @GetMapping({"/users"})
  public String listUsers(Model model) {
    List<User> users = this.userRepository.findAll();
    model.addAttribute("users", users);
    return "admin/users";
  }
  
  @GetMapping({"/users/details/{id}"})
  public String userDetails(@PathVariable Long id, Model model) {
    User user = this.userRepository.findById(id).orElseThrow();
    List<Order> userOrders = this.orderService.getUserOrders(user);
    
    model.addAttribute("user", user);
    model.addAttribute("orders", userOrders);
    return "admin/user-details";
  }
}


