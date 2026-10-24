/*     */ package org.springframework.boot.loader.log;
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
/*     */ final class SystemErrDebugLogger
/*     */   extends DebugLogger
/*     */ {
/*     */   private final String prefix;
/*     */   
/*     */   SystemErrDebugLogger(Class<?> sourceClass) {
/* 118 */     this.prefix = "LOADER: " + String.valueOf(sourceClass) + " : ";
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(String message) {
/* 123 */     print(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(String message, Object arg1) {
/* 128 */     print(message.formatted(new Object[] { arg1 }));
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(String message, Object arg1, Object arg2) {
/* 133 */     print(message.formatted(new Object[] { arg1, arg2 }));
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(String message, Object arg1, Object arg2, Object arg3) {
/* 138 */     print(message.formatted(new Object[] { arg1, arg2, arg3 }));
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(String message, Object arg1, Object arg2, Object arg3, Object arg4) {
/* 143 */     print(message.formatted(new Object[] { arg1, arg2, arg3, arg4 }));
/*     */   }
/*     */   
/*     */   private void print(String message) {
/* 147 */     System.err.println(this.prefix + this.prefix);
/*     */   }
/*     */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\org\springframework\boot\loader\log\DebugLogger$SystemErrDebugLogger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */