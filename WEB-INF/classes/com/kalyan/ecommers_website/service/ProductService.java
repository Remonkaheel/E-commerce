/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.service;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.Product;
/*    */ import com.kalyan.ecommers_website.repository.ProductRepository;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class ProductService
/*    */ {
/*    */   @Autowired
/*    */   private ProductRepository productRepository;
/*    */   
/*    */   public Product saveProduct(Product product) {
/* 19 */     return (Product)this.productRepository.save(product);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Product> getAllProducts() {
/* 24 */     return this.productRepository.findAll();
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<Product> getProductById(Long id) {
/* 29 */     return this.productRepository.findById(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Product> getProductsByCategory(Long categoryId) {
/* 34 */     return this.productRepository.findByCategoryId(categoryId);
/*    */   }
/*    */   
/*    */   public void updateProduct(Long id, Product updatedProduct) {
/* 38 */     Product product = (Product)this.productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("المنتج غير موجود: " + id));
/*    */     
/* 40 */     product.setName(updatedProduct.getName());
/* 41 */     product.setDescription(updatedProduct.getDescription());
/* 42 */     product.setPrice(updatedProduct.getPrice());
/* 43 */     product.setStock(updatedProduct.getStock());
/* 44 */     product.setCategory(updatedProduct.getCategory());
/*    */ 
/*    */     
/* 47 */     if (updatedProduct.getImageUrl1() != null) {
/* 48 */       product.setImageUrl1(updatedProduct.getImageUrl1());
/*    */     }
/* 50 */     if (updatedProduct.getImageUrl2() != null) {
/* 51 */       product.setImageUrl2(updatedProduct.getImageUrl2());
/*    */     }
/* 53 */     if (updatedProduct.getImageUrl3() != null) {
/* 54 */       product.setImageUrl3(updatedProduct.getImageUrl3());
/*    */     }
/*    */     
/* 57 */     this.productRepository.save(product);
/*    */   }
/*    */   
/*    */   public void deleteProductById(Long id) {
/* 61 */     this.productRepository.deleteById(id);
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\service\ProductService.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */