/*    */ package org.springframework.boot.loader.launch;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JarLauncher
/*    */   extends ExecutableArchiveLauncher
/*    */ {
/*    */   public JarLauncher() throws Exception {}
/*    */   
/*    */   protected JarLauncher(Archive archive) throws Exception {
/* 36 */     super(archive);
/*    */   }
/*    */   
/*    */   public static void main(String[] args) throws Exception {
/* 40 */     (new JarLauncher()).launch(args);
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\org\springframework\boot\loader\launch\JarLauncher.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */