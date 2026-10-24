/*     */ package WEB-INF.classes.com.kalyan.ecommers_website.config;
/*     */ 
/*     */ import com.kalyan.ecommers_website.config.SecurityConfig;
/*     */ import org.springframework.context.annotation.Configuration;
/*     */ import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
/*     */ import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Configuration
/*     */ public class MvcConfig
/*     */   implements WebMvcConfigurer
/*     */ {
/*     */   public void addViewControllers(ViewControllerRegistry registry) {
/* 106 */     registry.addViewController("/about").setViewName("about");
/* 107 */     registry.addViewController("/privacy").setViewName("privacy");
/* 108 */     registry.addViewController("/return").setViewName("return");
/* 109 */     registry.addViewController("/shipping").setViewName("shipping");
/*     */   }
/*     */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\config\SecurityConfig$MvcConfig.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */