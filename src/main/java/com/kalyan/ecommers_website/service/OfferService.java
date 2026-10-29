package com.kalyan.ecommers_website.service;

import com.kalyan.ecommers_website.model.Offer;
import com.kalyan.ecommers_website.repository.OfferRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OfferService
{
  @Autowired
  private OfferRepository offerRepository;
  
  public void saveOffer(Offer offer) {
    this.offerRepository.save(offer);
  }

  
  public List<Offer> getAllOffers() {
    return this.offerRepository.findAll();
  }
  public void deleteOfferById(Long id) {
    this.offerRepository.deleteById(id);
  }
}


