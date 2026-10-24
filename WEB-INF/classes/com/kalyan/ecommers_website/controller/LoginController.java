/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.controller;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.Category;
/*    */ import com.kalyan.ecommers_website.service.CategoryService;
/*    */ import jakarta.servlet.http.HttpServletRequest;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ public class LoginController
/*    */ {
/*    */   @Autowired
/*    */   private CategoryService categoryService;
/*    */   
/*    */   @GetMapping({"/login"})
/*    */   public String showLoginPage(Model model, HttpServletRequest request) {
/*    */     try {
/* 29 */       List<Category> categories = this.categoryService.getAllCategories();
/* 30 */       model.addAttribute("categories", categories);
/* 31 */       model.addAttribute("activeCategoryId", null);
/* 32 */       model.addAttribute("activePage", "login");
/* 33 */       model.addAttribute("cart", null);
/* 34 */     } catch (Exception e) {
/* 35 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 38 */     return "login";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/home"})
/*    */   public String signIn(Model model, HttpServletRequest request) {
/*    */     try {
/* 47 */       List<Category> categories = this.categoryService.getAllCategories();
/* 48 */       model.addAttribute("categories", categories);
/* 49 */       model.addAttribute("activeCategoryId", null);
/* 50 */       model.addAttribute("activePage", "login");
/* 51 */       model.addAttribute("cart", null);
/* 52 */     } catch (Exception e) {
/* 53 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 56 */     return "home";
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\controller\LoginController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */