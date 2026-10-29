package com.kalyan.ecommers_website.controller;

import com.kalyan.ecommers_website.model.Product;
import com.kalyan.ecommers_website.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping({"/api"})
public class SearchRestController
{
  @Autowired
  private ProductRepository productRepository;
  
  @GetMapping({"/search"})
  public List<Product> searchProducts(@RequestParam String keyword) {
    return this.productRepository.findTop10ByNameContainingIgnoreCase(keyword);
  }
}


