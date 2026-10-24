/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.config;
/*    */ 
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ public class WebConfig
/*    */   implements WebMvcConfigurer
/*    */ {
/*    */   public void addResourceHandlers(ResourceHandlerRegistry registry) {
/* 18 */     String uploadDir = System.getProperty("user.dir") + "/uploads/";
/* 19 */     registry.addResourceHandler(new String[] { "/images/**"
/* 20 */         }).addResourceLocations(new String[] { "file:" + uploadDir });
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\config\WebConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */