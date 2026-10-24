/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.service;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.Category;
/*    */ import com.kalyan.ecommers_website.repository.CategoryRepository;
/*    */ import com.kalyan.ecommers_website.repository.ProductRepository;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class CategoryService
/*    */ {
/*    */   @Autowired
/*    */   private CategoryRepository categoryRepository;
/*    */   @Autowired
/*    */   private ProductRepository productRepository;
/*    */   
/*    */   public List<Category> getAllCategories() {
/* 26 */     return this.categoryRepository.findAll();
/*    */   }
/*    */   
/*    */   public Optional<Category> getCategoryById(Long id) {
/* 30 */     return this.categoryRepository.findById(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public Category saveOrGetCategory(String categoryName) {
/* 35 */     Optional<Category> existingCategory = this.categoryRepository.findByName(categoryName);
/* 36 */     if (existingCategory.isPresent()) {
/* 37 */       return existingCategory.get();
/*    */     }
/* 39 */     Category newCategory = new Category();
/* 40 */     newCategory.setName(categoryName);
/* 41 */     return (Category)this.categoryRepository.save(newCategory);
/*    */   }
/*    */ 
/*    */   
/*    */   @Transactional
/*    */   public void deleteCategory(Long categoryId) {
/* 47 */     this.productRepository.deleteByCategoryId(categoryId);
/*    */ 
/*    */     
/* 50 */     this.categoryRepository.deleteById(categoryId);
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\service\CategoryService.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */