/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.controller;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.Category;
/*    */ import com.kalyan.ecommers_website.model.DTO.RegistrationDto;
/*    */ import com.kalyan.ecommers_website.service.CategoryService;
/*    */ import com.kalyan.ecommers_website.service.UserService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.ModelAttribute;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ public class RegistrationController
/*    */ {
/*    */   @Autowired
/*    */   private UserService userService;
/*    */   @Autowired
/*    */   private CategoryService categoryService;
/*    */   
/*    */   @GetMapping({"/register"})
/*    */   public String showRegistrationForm(Model model) {
/* 27 */     model.addAttribute("user", new RegistrationDto());
/*    */ 
/*    */     
/* 30 */     List<Category> categories = this.categoryService.getAllCategories();
/* 31 */     model.addAttribute("categories", categories);
/*    */     
/* 33 */     return "register";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @PostMapping({"/register"})
/*    */   public String registerUser(@ModelAttribute("user") RegistrationDto registrationDto, RedirectAttributes redirectAttributes, Model model) {
/* 40 */     String result = this.userService.saveNewUser(registrationDto);
/*    */ 
/*    */     
/* 43 */     List<Category> categories = this.categoryService.getAllCategories();
/* 44 */     model.addAttribute("categories", categories);
/*    */     
/* 46 */     if (!"success".equals(result)) {
/* 47 */       redirectAttributes.addFlashAttribute("error", result);
/* 48 */       return "redirect:/register";
/*    */     } 
/*    */     
/* 51 */     return "redirect:/login";
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\controller\RegistrationController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */