package com.kalyan.ecommers_website.service;

import com.kalyan.ecommers_website.model.Product;
import com.kalyan.ecommers_website.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProductService
{
  @Autowired
  private ProductRepository productRepository;
  
  public Product saveProduct(Product product) {
    return (Product)this.productRepository.save(product);
  }

  
  public List<Product> getAllProducts() {
    return this.productRepository.findAll();
  }

  
  public Optional<Product> getProductById(Long id) {
    return this.productRepository.findById(id);
  }

  
  public List<Product> getProductsByCategory(Long categoryId) {
    return this.productRepository.findByCategoryId(categoryId);
  }
  
  public void updateProduct(Long id, Product updatedProduct) {
    Product product = (Product)this.productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("المنتج غير موجود: " + id));
    
    product.setName(updatedProduct.getName());
    product.setDescription(updatedProduct.getDescription());
    product.setPrice(updatedProduct.getPrice());
    product.setStock(updatedProduct.getStock());
    product.setCategory(updatedProduct.getCategory());

    
    if (updatedProduct.getImageUrl1() != null) {
      product.setImageUrl1(updatedProduct.getImageUrl1());
    }
    if (updatedProduct.getImageUrl2() != null) {
      product.setImageUrl2(updatedProduct.getImageUrl2());
    }
    if (updatedProduct.getImageUrl3() != null) {
      product.setImageUrl3(updatedProduct.getImageUrl3());
    }
    
    this.productRepository.save(product);
  }
  
  public void deleteProductById(Long id) {
    this.productRepository.deleteById(id);
  }
}


