/*    */ package org.springframework.boot.loader.net.protocol.nested;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.InetAddress;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import java.net.URLStreamHandler;
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
/*    */ 
/*    */ public class Handler
/*    */   extends URLStreamHandler
/*    */ {
/*    */   private static final String PREFIX = "nested:";
/*    */   
/*    */   protected InetAddress getHostAddress(URL url) {
/* 43 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected URLConnection openConnection(URL url) throws IOException {
/* 48 */     return new NestedUrlConnection(url);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void assertUrlIsNotMalformed(String url) {
/* 56 */     if (url == null || !url.startsWith("nested:")) {
/* 57 */       throw new IllegalArgumentException("'url' must not be null and must use 'nested' protocol");
/*    */     }
/* 59 */     NestedLocation.parse(url.substring("nested:".length()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void clearCache() {
/* 66 */     NestedLocation.clearCache();
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\org\springframework\boot\loader\net\protocol\nested\Handler.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */