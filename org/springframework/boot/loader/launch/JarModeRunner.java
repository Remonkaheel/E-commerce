/*    */ package org.springframework.boot.loader.launch;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.springframework.boot.loader.jarmode.JarMode;
/*    */ import org.springframework.boot.loader.jarmode.JarModeErrorException;
/*    */ import org.springframework.core.io.support.SpringFactoriesLoader;
/*    */ import org.springframework.util.ClassUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class JarModeRunner
/*    */ {
/* 33 */   static final String DISABLE_SYSTEM_EXIT = JarModeRunner.class.getName() + ".DISABLE_SYSTEM_EXIT";
/*    */   
/* 35 */   static final String SUPPRESSED_SYSTEM_EXIT_CODE = JarModeRunner.class.getName() + ".SUPPRESSED_SYSTEM_EXIT_CODE";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static void main(String[] args) {
/* 41 */     String mode = System.getProperty("jarmode");
/* 42 */     boolean disableSystemExit = Boolean.getBoolean(DISABLE_SYSTEM_EXIT);
/*    */     try {
/* 44 */       runJarMode(mode, args);
/* 45 */       if (disableSystemExit) {
/* 46 */         System.setProperty(SUPPRESSED_SYSTEM_EXIT_CODE, "0");
/*    */       }
/*    */     }
/* 49 */     catch (Throwable ex) {
/* 50 */       printError(ex);
/* 51 */       if (disableSystemExit) {
/* 52 */         System.setProperty(SUPPRESSED_SYSTEM_EXIT_CODE, "1");
/*    */         return;
/*    */       } 
/* 55 */       System.exit(1);
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void runJarMode(String mode, String[] args) {
/* 60 */     List<JarMode> candidates = SpringFactoriesLoader.loadFactories(JarMode.class, 
/* 61 */         ClassUtils.getDefaultClassLoader());
/* 62 */     for (JarMode candidate : candidates) {
/* 63 */       if (candidate.accepts(mode)) {
/* 64 */         candidate.run(mode, args);
/*    */         return;
/*    */       } 
/*    */     } 
/* 68 */     throw new JarModeErrorException("Unsupported jarmode '" + mode + "'");
/*    */   }
/*    */   
/*    */   private static void printError(Throwable ex) {
/* 72 */     if (ex instanceof JarModeErrorException) {
/* 73 */       String message = ex.getMessage();
/* 74 */       System.err.println("Error: " + message);
/* 75 */       System.err.println();
/*    */       return;
/*    */     } 
/* 78 */     ex.printStackTrace();
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\org\springframework\boot\loader\launch\JarModeRunner.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */