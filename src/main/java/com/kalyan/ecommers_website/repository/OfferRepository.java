package com.kalyan.ecommers_website.repository;

import com.kalyan.ecommers_website.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {}


