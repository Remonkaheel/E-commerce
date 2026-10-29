package com.kalyan.ecommers_website.service;

import com.kalyan.ecommers_website.model.Advertisement;
import com.kalyan.ecommers_website.repository.AdvertisementRepository;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdvertisementService {
  private final AdvertisementRepository advertisementRepository;
  private static final String UPLOAD_DIR = "uploads/advertisements/";
  
  public AdvertisementService(AdvertisementRepository advertisementRepository) {
    this.advertisementRepository = advertisementRepository;
  }


  
  public List<Advertisement> getAllAdvertisements() {
    return this.advertisementRepository.findAll();
  }
  
  public List<Advertisement> getActiveAdvertisements() {
    return this.advertisementRepository.findByIsActiveTrueOrderByDisplayOrderAsc();
  }



  
  public Advertisement saveAdvertisement(String title, MultipartFile fileDesktop, MultipartFile fileMobile, Integer displayOrder, Boolean isActive) throws IOException {
    String desktopUrl = uploadFile(fileDesktop);
    String mobileUrl = uploadFile(fileMobile);
    
    Advertisement ad = new Advertisement();
    ad.setTitle(title);
    ad.setImageUrlDesktop(desktopUrl);
    ad.setImageUrlMobile(mobileUrl);
    ad.setDisplayOrder(displayOrder);
    ad.setIsActive(isActive);
    
    return (Advertisement)this.advertisementRepository.save(ad);
  }
  
  public void toggleActive(Long id) {
    Advertisement ad = this.advertisementRepository.findById(id).orElseThrow();
    ad.setIsActive(Boolean.valueOf(!ad.getIsActive().booleanValue()));
    this.advertisementRepository.save(ad);
  }
  
  public void deleteAdvertisement(Long id) {
    this.advertisementRepository.deleteById(id);
  }
  
  private String uploadFile(MultipartFile file) throws IOException {
    if (file == null || file.isEmpty()) {
      return null;
    }
    
    String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/advertisements/";
    
    Path uploadPath = Paths.get(UPLOAD_DIR, new String[0]);
    if (!Files.exists(uploadPath, new java.nio.file.LinkOption[0])) {
      Files.createDirectories(uploadPath, (FileAttribute<?>[])new FileAttribute[0]);
    }
    
    String originalFilename = file.getOriginalFilename();
    String fileExtension = "";
    if (originalFilename != null && originalFilename.contains(".")) {
      fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
    }
    
    String uniqueFileName = UUID.randomUUID().toString() + UUID.randomUUID().toString();
    Path destinationPath = uploadPath.resolve(uniqueFileName);
    Files.copy(file.getInputStream(), destinationPath, new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
    
    System.out.println("✅ Saved to: " + destinationPath.toAbsolutePath());

    
    return "/images/advertisements/" + uniqueFileName;
  }
}


