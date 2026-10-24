/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.service;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.Offer;
/*    */ import com.kalyan.ecommers_website.repository.OfferRepository;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class OfferService
/*    */ {
/*    */   @Autowired
/*    */   private OfferRepository offerRepository;
/*    */   
/*    */   public void saveOffer(Offer offer) {
/* 17 */     this.offerRepository.save(offer);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Offer> getAllOffers() {
/* 22 */     return this.offerRepository.findAll();
/*    */   }
/*    */   public void deleteOfferById(Long id) {
/* 25 */     this.offerRepository.deleteById(id);
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\service\OfferService.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */