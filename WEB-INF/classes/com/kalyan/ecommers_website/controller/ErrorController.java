/*    */ package WEB-INF.classes.com.kalyan.ecommers_website.controller;
/*    */ 
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ 
/*    */ @Controller
/*    */ public class ErrorController
/*    */ {
/*    */   @GetMapping({"/access-denied"})
/*    */   public String accessDenied() {
/* 11 */     return "access-denied";
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\WEB-INF\classes\com\kalyan\ecommers_website\controller\ErrorController.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */