/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.controller;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.Category;
/*    */ import com.kalyan.ecommers_website.service.CategoryService;
/*    */ import com.kalyan.ecommers_website.service.ProductService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.http.ResponseEntity;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.DeleteMapping;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ public class CategoryController
/*    */ {
/*    */   @Autowired
/*    */   private CategoryService categoryService;
/*    */   @Autowired
/*    */   private ProductService productService;
/*    */   
/*    */   @GetMapping({"/category/{id}"})
/*    */   public String viewCategoryProducts(@PathVariable("id") Long categoryId, Model model) {
/* 27 */     Category category = this.categoryService.getCategoryById(categoryId).orElseThrow();
/* 28 */     model.addAttribute("category", category);
/* 29 */     model.addAttribute("products", this.productService.getProductsByCategory(categoryId));
/* 30 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 31 */     model.addAttribute("activeCategoryId", categoryId);
/* 32 */     return "category-products";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @DeleteMapping({"/admin/categories/{id}"})
/*    */   @ResponseBody
/*    */   public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
/*    */     try {
/* 41 */       this.categoryService.deleteCategory(id);
/* 42 */       return ResponseEntity.ok().build();
/* 43 */     } catch (Exception e) {
/* 44 */       return ResponseEntity.badRequest()
/* 45 */         .body("{\"error\": \"لا يمكن حذف الصنف\"}");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @GetMapping({"/admin/categories"})
/*    */   public String categoriesPage(Model model) {
/* 54 */     model.addAttribute("categories", this.categoryService.getAllCategories());
/* 55 */     return "admin-categories";
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\controller\CategoryController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */