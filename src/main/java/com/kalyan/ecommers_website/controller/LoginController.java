package com.kalyan.ecommers_website.controller;

import com.kalyan.ecommers_website.model.Category;
import com.kalyan.ecommers_website.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;









@Controller
public class LoginController
{
  @Autowired
  private CategoryService categoryService;
  
  @GetMapping({"/login"})
  public String showLoginPage(Model model, HttpServletRequest request) {
    try {
      List<Category> categories = this.categoryService.getAllCategories();
      model.addAttribute("categories", categories);
      model.addAttribute("activeCategoryId", null);
      model.addAttribute("activePage", "login");
      model.addAttribute("cart", null);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    
    return "login";
  }



  
  @GetMapping({"/home"})
  public String signIn(Model model, HttpServletRequest request) {
    try {
      List<Category> categories = this.categoryService.getAllCategories();
      model.addAttribute("categories", categories);
      model.addAttribute("activeCategoryId", null);
      model.addAttribute("activePage", "login");
      model.addAttribute("cart", null);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    
    return "home";
  }
}


