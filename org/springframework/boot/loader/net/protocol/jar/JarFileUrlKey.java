/*    */ package org.springframework.boot.loader.net.protocol.jar;
/*    */ 
/*    */ import java.net.URL;
/*    */ import java.util.Objects;
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
/*    */ final class JarFileUrlKey
/*    */ {
/*    */   private final String protocol;
/*    */   private final String host;
/*    */   private final int port;
/*    */   private final String file;
/*    */   private final boolean runtimeRef;
/*    */   
/*    */   JarFileUrlKey(URL url) {
/* 40 */     this.protocol = url.getProtocol();
/* 41 */     this.host = url.getHost();
/* 42 */     this.port = (url.getPort() != -1) ? url.getPort() : url.getDefaultPort();
/* 43 */     this.file = url.getFile();
/* 44 */     this.runtimeRef = "runtime".equals(url.getRef());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 49 */     if (this == obj) {
/* 50 */       return true;
/*    */     }
/* 52 */     if (obj == null || getClass() != obj.getClass()) {
/* 53 */       return false;
/*    */     }
/* 55 */     JarFileUrlKey other = (JarFileUrlKey)obj;
/*    */     
/* 57 */     return (Objects.equals(this.file, other.file) && equalsIgnoringCase(this.protocol, other.protocol) && 
/* 58 */       equalsIgnoringCase(this.host, other.host) && this.port == other.port && this.runtimeRef == other.runtimeRef);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 64 */     return Objects.hashCode(this.file);
/*    */   }
/*    */   
/*    */   private boolean equalsIgnoringCase(String s1, String s2) {
/* 68 */     return (s1 == s2 || (s1 != null && s1.equalsIgnoreCase(s2)));
/*    */   }
/*    */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\org\springframework\boot\loader\net\protocol\jar\JarFileUrlKey.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */