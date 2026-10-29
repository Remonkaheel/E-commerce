package com.kalyan.ecommers_website.controller;

import com.kalyan.ecommers_website.model.Advertisement;
import com.kalyan.ecommers_website.model.Category;
import com.kalyan.ecommers_website.model.Product;
import com.kalyan.ecommers_website.repository.ProductRepository;
import com.kalyan.ecommers_website.service.AdvertisementService;
import com.kalyan.ecommers_website.service.CartService;
import com.kalyan.ecommers_website.service.CategoryService;
import com.kalyan.ecommers_website.service.OfferService;
import com.kalyan.ecommers_website.service.ProductService;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;






@Controller
public class HomeController
{
  @Autowired
  private OfferService offerService;
  @Autowired
  private ProductService productService;
  @Autowired
  private CategoryService categoryService;
  @Autowired
  private AdvertisementService advertisementService;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private CartService cartService;
  
  @GetMapping({"/"})
  public String home(HttpSession session, Model model) {
    model.addAttribute("offers", this.offerService.getAllOffers());
    model.addAttribute("categories", this.categoryService.getAllCategories());
    model.addAttribute("activeCategoryId", null);
    session.setAttribute("init", Boolean.valueOf(true));
    model.addAttribute("activePage", "home");
    Map<Category, Product> categoryProducts = new HashMap<>();
    List<Category> categories = this.categoryService.getAllCategories();
    for (Category category : categories) {
      List<Product> products = this.productService.getProductsByCategory(category.getId());
      if (!products.isEmpty())
      {
        categoryProducts.put(category, products.get(0));
      }
    } 
    model.addAttribute("categoryProducts", categoryProducts);
    List<Advertisement> advertisements = this.advertisementService.getActiveAdvertisements();
    model.addAttribute("advertisements", advertisements);
    
    return "home";
  }
  @GetMapping({"/contact"})
  public String contact(Model model) {
    model.addAttribute("categories", this.categoryService.getAllCategories());
    model.addAttribute("activeCategoryId", null);
    model.addAttribute("activePage", "contact");
    return "contact";
  }



  
  @GetMapping({"/product/{id}"})
  public String productDetails(@PathVariable Long id, Model model) {
    Product product = this.productService.getProductById(id).orElse(null);
    
    if (product == null) {
      return "redirect:/";
    }

    
    List<Product> relatedProducts = this.productRepository.findByCategoryId(product
        .getCategory().getId());
    
    model.addAttribute("relatedProducts", relatedProducts);


    
    model.addAttribute("categories", this.categoryService.getAllCategories());

    
    model.addAttribute("product", product);
    model.addAttribute("relatedProducts", relatedProducts);


    
    model.addAttribute("activePage", "product");
    model.addAttribute("activeCategoryId", product.getCategory().getId());
    
    addCartInfoToModel(model);
    
    model.addAttribute("product", product);
    model.addAttribute("relatedProducts", relatedProducts);
    model.addAttribute("activePage", "product");
    
    return "product-details";
  }



  
  private void addCartInfoToModel(Model model) {
    try {
      if (this.cartService != null) {
        model.addAttribute("cartItemCount", Integer.valueOf(this.cartService.getItemCount()));
        model.addAttribute("cartTotal", this.cartService.getTotal());
        model.addAttribute("cartItems", this.cartService.getItems());
      } else {
        model.addAttribute("cartItemCount", Integer.valueOf(0));
        model.addAttribute("cartTotal", "0");
        model.addAttribute("cartItems", List.of());
      } 
    } catch (Exception e) {
      model.addAttribute("cartItemCount", Integer.valueOf(0));
      model.addAttribute("cartTotal", "0");
      model.addAttribute("cartItems", List.of());
    } 
  }
}


