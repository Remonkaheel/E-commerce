package org.springframework.boot.loader.log;

final class DisabledDebugLogger extends DebugLogger {
  public void log(String message) {}
  
  public void log(String message, Object arg1) {}
  
  public void log(String message, Object arg1, Object arg2) {}
  
  public void log(String message, Object arg1, Object arg2, Object arg3) {}
  
  public void log(String message, Object arg1, Object arg2, Object arg3, Object arg4) {}
}


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\org\springframework\boot\loader\log\DebugLogger$DisabledDebugLogger.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */