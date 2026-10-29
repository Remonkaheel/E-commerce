package com.kalyan.ecommers_website.repository;

import com.kalyan.ecommers_website.model.Order;
import com.kalyan.ecommers_website.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByUser(User paramUser);
  
  List<Order> findByUserOrderByOrderDateDesc(User paramUser);
}


