package com.kalyan.ecommers_website.controller;

import com.kalyan.ecommers_website.model.Category;
import com.kalyan.ecommers_website.service.CategoryService;
import com.kalyan.ecommers_website.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class CategoryController
{
  @Autowired
  private CategoryService categoryService;
  @Autowired
  private ProductService productService;
  
  @GetMapping({"/category/{id}"})
  public String viewCategoryProducts(@PathVariable("id") Long categoryId, Model model) {
    Category category = this.categoryService.getCategoryById(categoryId).orElseThrow();
    model.addAttribute("category", category);
    model.addAttribute("products", this.productService.getProductsByCategory(categoryId));
    model.addAttribute("categories", this.categoryService.getAllCategories());
    model.addAttribute("activeCategoryId", categoryId);
    return "category-products";
  }


  
  @DeleteMapping({"/admin/categories/{id}"})
  @ResponseBody
  public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
    try {
      this.categoryService.deleteCategory(id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest()
        .body("{\"error\": \"لا يمكن حذف الصنف\"}");
    } 
  }



  
  @GetMapping({"/admin/categories"})
  public String categoriesPage(Model model) {
    model.addAttribute("categories", this.categoryService.getAllCategories());
    return "admin-categories";
  }
}


