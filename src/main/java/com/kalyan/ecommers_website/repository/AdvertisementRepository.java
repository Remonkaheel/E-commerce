package com.kalyan.ecommers_website.repository;

import com.kalyan.ecommers_website.model.Advertisement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
  List<Advertisement> findByIsActiveTrueOrderByDisplayOrderAsc();
}


