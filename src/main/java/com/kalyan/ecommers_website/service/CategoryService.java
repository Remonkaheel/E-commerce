package com.kalyan.ecommers_website.service;

import com.kalyan.ecommers_website.model.Category;
import com.kalyan.ecommers_website.repository.CategoryRepository;
import com.kalyan.ecommers_website.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;






@Service
public class CategoryService
{
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private ProductRepository productRepository;
  
  public List<Category> getAllCategories() {
    return this.categoryRepository.findAll();
  }
  
  public Optional<Category> getCategoryById(Long id) {
    return this.categoryRepository.findById(id);
  }

  
  public Category saveOrGetCategory(String categoryName) {
    Optional<Category> existingCategory = this.categoryRepository.findByName(categoryName);
    if (existingCategory.isPresent()) {
      return existingCategory.get();
    }
    Category newCategory = new Category();
    newCategory.setName(categoryName);
    return (Category)this.categoryRepository.save(newCategory);
  }

  
  @Transactional
  public void deleteCategory(Long categoryId) {
    this.productRepository.deleteByCategoryId(categoryId);

    
    this.categoryRepository.deleteById(categoryId);
  }
}


