package com.kalyan.ecommers_website.controller;

import com.kalyan.ecommers_website.service.AdvertisementService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/admin/advertisements"})
public class AdvertisementController
{
  @Autowired
  private AdvertisementService advertisementService;
  
  @GetMapping
  public String listAdvertisements(Model model) {
    model.addAttribute("advertisements", this.advertisementService.getAllAdvertisements());
    return "admin/advertisements";
  }

  
  @GetMapping({"/add"})
  public String showAddForm() {
    return "admin/add-advertisement";
  }







  
  @PostMapping({"/add"})
  public String addAdvertisement(@RequestParam("title") String title, @RequestParam("fileDesktop") MultipartFile fileDesktop, @RequestParam("fileMobile") MultipartFile fileMobile, @RequestParam("displayOrder") Integer displayOrder, @RequestParam(value = "isActive", defaultValue = "false") Boolean isActive) throws IOException {
    this.advertisementService.saveAdvertisement(title, fileDesktop, fileMobile, displayOrder, isActive);
    return "redirect:/admin/advertisements";
  }

  
  @PostMapping({"/toggle/{id}"})
  public String toggleAdvertisement(@PathVariable Long id) {
    this.advertisementService.toggleActive(id);
    return "redirect:/admin/advertisements";
  }

  
  @GetMapping({"/delete/{id}"})
  public String deleteAdvertisement(@PathVariable Long id) {
    this.advertisementService.deleteAdvertisement(id);
    return "redirect:/admin/advertisements";
  }
}


