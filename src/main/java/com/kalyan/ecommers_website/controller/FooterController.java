package com.kalyan.ecommers_website.controller;

import com.kalyan.ecommers_website.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class FooterController
{
  @Autowired
  private CategoryService categoryService;
  
  @GetMapping({"/about"})
  public String about(Model model) {
    model.addAttribute("categories", this.categoryService.getAllCategories());
    model.addAttribute("activeCategoryId", null);
    return "about";
  }
  
  @GetMapping({"/privacy"})
  public String privacy(Model model) {
    model.addAttribute("categories", this.categoryService.getAllCategories());
    model.addAttribute("activeCategoryId", null);
    return "privacy";
  }
  
  @GetMapping({"/return"})
  public String returnPolicy(Model model) {
    model.addAttribute("categories", this.categoryService.getAllCategories());
    model.addAttribute("activeCategoryId", null);
    return "return";
  }
  
  @GetMapping({"/shipping"})
  public String shipping(Model model) {
    model.addAttribute("categories", this.categoryService.getAllCategories());
    model.addAttribute("activeCategoryId", null);
    return "shipping";
  }
}


