package org.springframework.boot.loader.jarmode;

public interface JarMode {
  boolean accepts(String paramString);
  
  void run(String paramString, String[] paramArrayOfString) throws JarModeErrorException;
}


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\org\springframework\boot\loader\jarmode\JarMode.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */