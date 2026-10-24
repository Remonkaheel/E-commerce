/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.controller;
/*    */ 
/*    */ import com.kalyan.ecommers_website.service.CategoryService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ public class FooterController
/*    */ {
/*    */   @Autowired
/*    */   private CategoryService categoryService;
/*    */   
/*    */   @GetMapping({"/about"})
/*    */   public String about(Model model) {
/* 19 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 20 */     model.addAttribute("activeCategoryId", null);
/* 21 */     return "about";
/*    */   }
/*    */   
/*    */   @GetMapping({"/privacy"})
/*    */   public String privacy(Model model) {
/* 26 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 27 */     model.addAttribute("activeCategoryId", null);
/* 28 */     return "privacy";
/*    */   }
/*    */   
/*    */   @GetMapping({"/return"})
/*    */   public String returnPolicy(Model model) {
/* 33 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 34 */     model.addAttribute("activeCategoryId", null);
/* 35 */     return "return";
/*    */   }
/*    */   
/*    */   @GetMapping({"/shipping"})
/*    */   public String shipping(Model model) {
/* 40 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 41 */     model.addAttribute("activeCategoryId", null);
/* 42 */     return "shipping";
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\controller\FooterController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */