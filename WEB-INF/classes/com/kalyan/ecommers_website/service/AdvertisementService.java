/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.service;
/*    */ 
/*    */ import com.kalyan.ecommers_website.model.Advertisement;
/*    */ import com.kalyan.ecommers_website.repository.AdvertisementRepository;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.CopyOption;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.nio.file.StandardCopyOption;
/*    */ import java.nio.file.attribute.FileAttribute;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ 
/*    */ @Service
/*    */ public class AdvertisementService {
/*    */   private final AdvertisementRepository advertisementRepository;
/*    */   private static final String UPLOAD_DIR = "uploads/advertisements/";
/*    */   
/*    */   public AdvertisementService(AdvertisementRepository advertisementRepository) {
/* 23 */     this.advertisementRepository = advertisementRepository;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Advertisement> getAllAdvertisements() {
/* 29 */     return this.advertisementRepository.findAll();
/*    */   }
/*    */   
/*    */   public List<Advertisement> getActiveAdvertisements() {
/* 33 */     return this.advertisementRepository.findByIsActiveTrueOrderByDisplayOrderAsc();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Advertisement saveAdvertisement(String title, MultipartFile fileDesktop, MultipartFile fileMobile, Integer displayOrder, Boolean isActive) throws IOException {
/* 40 */     String desktopUrl = uploadFile(fileDesktop);
/* 41 */     String mobileUrl = uploadFile(fileMobile);
/*    */     
/* 43 */     Advertisement ad = new Advertisement();
/* 44 */     ad.setTitle(title);
/* 45 */     ad.setImageUrlDesktop(desktopUrl);
/* 46 */     ad.setImageUrlMobile(mobileUrl);
/* 47 */     ad.setDisplayOrder(displayOrder);
/* 48 */     ad.setIsActive(isActive);
/*    */     
/* 50 */     return (Advertisement)this.advertisementRepository.save(ad);
/*    */   }
/*    */   
/*    */   public void toggleActive(Long id) {
/* 54 */     Advertisement ad = this.advertisementRepository.findById(id).orElseThrow();
/* 55 */     ad.setIsActive(Boolean.valueOf(!ad.getIsActive().booleanValue()));
/* 56 */     this.advertisementRepository.save(ad);
/*    */   }
/*    */   
/*    */   public void deleteAdvertisement(Long id) {
/* 60 */     this.advertisementRepository.deleteById(id);
/*    */   }
/*    */   
/*    */   private String uploadFile(MultipartFile file) throws IOException {
/* 64 */     if (file == null || file.isEmpty()) {
/* 65 */       return null;
/*    */     }
/*    */     
/* 68 */     String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/advertisements/";
/*    */     
/* 70 */     Path uploadPath = Paths.get(UPLOAD_DIR, new String[0]);
/* 71 */     if (!Files.exists(uploadPath, new java.nio.file.LinkOption[0])) {
/* 72 */       Files.createDirectories(uploadPath, (FileAttribute<?>[])new FileAttribute[0]);
/*    */     }
/*    */     
/* 75 */     String originalFilename = file.getOriginalFilename();
/* 76 */     String fileExtension = "";
/* 77 */     if (originalFilename != null && originalFilename.contains(".")) {
/* 78 */       fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
/*    */     }
/*    */     
/* 81 */     String uniqueFileName = UUID.randomUUID().toString() + UUID.randomUUID().toString();
/* 82 */     Path destinationPath = uploadPath.resolve(uniqueFileName);
/* 83 */     Files.copy(file.getInputStream(), destinationPath, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
/*    */     
/* 85 */     System.out.println("✅ Saved to: " + destinationPath.toAbsolutePath());
/*    */ 
/*    */     
/* 88 */     return "/images/advertisements/" + uniqueFileName;
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\service\AdvertisementService.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */