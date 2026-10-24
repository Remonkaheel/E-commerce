/*    */ package WEB-INF.classes.com.kalyan.ecommers_website;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import javax.sql.DataSource;
/*    */ import org.springframework.boot.CommandLineRunner;
/*    */ import org.springframework.boot.SpringApplication;
/*    */ import org.springframework.boot.autoconfigure.SpringBootApplication;
/*    */ import org.springframework.boot.builder.SpringApplicationBuilder;
/*    */ import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ 
/*    */ @SpringBootApplication
/*    */ public class EcommersWebsiteApplication
/*    */   extends SpringBootServletInitializer {
/*    */   public static void main(String[] args) {
/* 16 */     SpringApplication.run(com.kalyan.ecommers_website.EcommersWebsiteApplication.class, args);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
/* 22 */     return application.sources(new Class[] { com.kalyan.ecommers_website.EcommersWebsiteApplication.class });
/*    */   }
/*    */   
/*    */   @Bean
/*    */   CommandLineRunner testDB(DataSource dataSource) {
/* 27 */     return args -> { System.out.println("✅ DataSource = " + dataSource); try { Connection conn = dataSource.getConnection(); try { System.out.println("✅ Connected to DB: " + conn.getMetaData().getURL()); if (conn != null)
/*    */               conn.close();  }
/* 29 */           catch (Throwable throwable) { if (conn != null) try { conn.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
/*    */            }
/* 31 */         catch (Exception e)
/*    */         { System.out.println("❌ Database connection failed: " + e.getMessage()); }
/*    */       
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\EcommersWebsiteApplication.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */