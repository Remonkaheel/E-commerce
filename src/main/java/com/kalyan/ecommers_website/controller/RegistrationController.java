package com.kalyan.ecommers_website.controller;

import com.kalyan.ecommers_website.model.Category;
import com.kalyan.ecommers_website.model.DTO.RegistrationDto;
import com.kalyan.ecommers_website.service.CategoryService;
import com.kalyan.ecommers_website.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RegistrationController
{
  @Autowired
  private UserService userService;
  @Autowired
  private CategoryService categoryService;
  
  @GetMapping({"/register"})
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new RegistrationDto());

    
    List<Category> categories = this.categoryService.getAllCategories();
    model.addAttribute("categories", categories);
    
    return "register";
  }


  
  @PostMapping({"/register"})
  public String registerUser(@ModelAttribute("user") RegistrationDto registrationDto, RedirectAttributes redirectAttributes, Model model) {
    String result = this.userService.saveNewUser(registrationDto);

    
    List<Category> categories = this.categoryService.getAllCategories();
    model.addAttribute("categories", categories);
    
    if (!"success".equals(result)) {
      redirectAttributes.addFlashAttribute("error", result);
      return "redirect:/register";
    } 
    
    return "redirect:/login";
  }
}


