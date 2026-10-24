/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.controller;
/*    */ 
/*    */ import com.kalyan.ecommers_website.service.AdvertisementService;
/*    */ import java.io.IOException;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.Model;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.PathVariable;
/*    */ import org.springframework.web.bind.annotation.PostMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ import org.springframework.web.multipart.MultipartFile;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/admin/advertisements"})
/*    */ public class AdvertisementController
/*    */ {
/*    */   @Autowired
/*    */   private AdvertisementService advertisementService;
/*    */   
/*    */   @GetMapping
/*    */   public String listAdvertisements(Model model) {
/* 24 */     model.addAttribute("advertisements", this.advertisementService.getAllAdvertisements());
/* 25 */     return "admin/advertisements";
/*    */   }
/*    */ 
/*    */   
/*    */   @GetMapping({"/add"})
/*    */   public String showAddForm() {
/* 31 */     return "admin/add-advertisement";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @PostMapping({"/add"})
/*    */   public String addAdvertisement(@RequestParam("title") String title, @RequestParam("fileDesktop") MultipartFile fileDesktop, @RequestParam("fileMobile") MultipartFile fileMobile, @RequestParam("displayOrder") Integer displayOrder, @RequestParam(value = "isActive", defaultValue = "false") Boolean isActive) throws IOException {
/* 43 */     this.advertisementService.saveAdvertisement(title, fileDesktop, fileMobile, displayOrder, isActive);
/* 44 */     return "redirect:/admin/advertisements";
/*    */   }
/*    */ 
/*    */   
/*    */   @PostMapping({"/toggle/{id}"})
/*    */   public String toggleAdvertisement(@PathVariable Long id) {
/* 50 */     this.advertisementService.toggleActive(id);
/* 51 */     return "redirect:/admin/advertisements";
/*    */   }
/*    */ 
/*    */   
/*    */   @GetMapping({"/delete/{id}"})
/*    */   public String deleteAdvertisement(@PathVariable Long id) {
/* 57 */     this.advertisementService.deleteAdvertisement(id);
/* 58 */     return "redirect:/admin/advertisements";
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\controller\AdvertisementController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */