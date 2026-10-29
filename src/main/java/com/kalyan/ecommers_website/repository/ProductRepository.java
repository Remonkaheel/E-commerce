package com.kalyan.ecommers_website.repository;

import com.kalyan.ecommers_website.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findTop10ByNameContainingIgnoreCase(String paramString);
  
  List<Product> findTop4ByCategoryIdAndIdNot(Long paramLong1, Long paramLong2);
  
  void deleteByCategoryId(Long paramLong);
  
  List<Product> findByCategoryId(Long paramLong);
}


