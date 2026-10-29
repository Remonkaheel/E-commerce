package com.kalyan.ecommers_website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;







@Configuration
public class WebConfig
  implements WebMvcConfigurer
{
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String uploadDir = System.getProperty("user.dir") + "/uploads/";
    registry.addResourceHandler(new String[] { "/images/**"
        }).addResourceLocations(new String[] { "file:" + uploadDir });
  }
}


