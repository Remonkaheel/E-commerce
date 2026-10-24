/*     */ package org.springframework.boot.loader.launch;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.UncheckedIOException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import java.util.jar.Attributes;
/*     */ import java.util.jar.Manifest;
/*     */ import org.springframework.boot.loader.net.protocol.Handlers;
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
/*     */ public abstract class Launcher
/*     */ {
/*  42 */   private static final String JAR_MODE_RUNNER_CLASS_NAME = JarModeRunner.class.getName();
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final String BOOT_CLASSPATH_INDEX_ATTRIBUTE = "Spring-Boot-Classpath-Index";
/*     */ 
/*     */   
/*     */   protected static final String DEFAULT_CLASSPATH_INDEX_FILE_NAME = "classpath.idx";
/*     */ 
/*     */   
/*     */   protected ClassPathIndexFile classPathIndex;
/*     */ 
/*     */ 
/*     */   
/*     */   protected void launch(String[] args) throws Exception {
/*  57 */     if (!isExploded()) {
/*  58 */       Handlers.register();
/*     */     }
/*     */     try {
/*  61 */       ClassLoader classLoader = createClassLoader(getClassPathUrls());
/*  62 */       String jarMode = System.getProperty("jarmode");
/*  63 */       String mainClassName = hasLength(jarMode) ? JAR_MODE_RUNNER_CLASS_NAME : getMainClass();
/*  64 */       launch(classLoader, mainClassName, args);
/*     */     }
/*  66 */     catch (UncheckedIOException ex) {
/*  67 */       throw ex.getCause();
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean hasLength(String jarMode) {
/*  72 */     return (jarMode != null && !jarMode.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassLoader createClassLoader(Collection<URL> urls) throws Exception {
/*  82 */     return createClassLoader(urls.<URL>toArray(new URL[0]));
/*     */   }
/*     */   
/*     */   private ClassLoader createClassLoader(URL[] urls) {
/*  86 */     ClassLoader parent = getClass().getClassLoader();
/*  87 */     return (ClassLoader)new LaunchedClassLoader(isExploded(), getArchive(), urls, parent);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void launch(ClassLoader classLoader, String mainClassName, String[] args) throws Exception {
/*  98 */     Thread.currentThread().setContextClassLoader(classLoader);
/*  99 */     Class<?> mainClass = Class.forName(mainClassName, false, classLoader);
/* 100 */     Method mainMethod = getMainMethod(mainClass);
/* 101 */     mainMethod.setAccessible(true);
/* 102 */     if (mainMethod.getParameterCount() == 0) {
/* 103 */       mainMethod.invoke(null, new Object[0]);
/*     */     } else {
/*     */       
/* 106 */       mainMethod.invoke(null, new Object[] { args });
/*     */     } 
/*     */   }
/*     */   
/*     */   private Method getMainMethod(Class<?> mainClass) throws Exception {
/*     */     try {
/* 112 */       return mainClass.getDeclaredMethod("main", new Class[] { String[].class });
/*     */     }
/* 114 */     catch (NoSuchMethodException ex) {
/* 115 */       return mainClass.getDeclaredMethod("main", new Class[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isExploded() {
/* 126 */     Archive archive = getArchive();
/* 127 */     return (archive != null && archive.isExploded());
/*     */   }
/*     */   
/*     */   ClassPathIndexFile getClassPathIndex(Archive archive) throws IOException {
/* 131 */     if (!archive.isExploded()) {
/* 132 */       return null;
/*     */     }
/* 134 */     String location = getClassPathIndexFileLocation(archive);
/* 135 */     return ClassPathIndexFile.loadIfPossible(archive.getRootDirectory(), location);
/*     */   }
/*     */   
/*     */   private String getClassPathIndexFileLocation(Archive archive) throws IOException {
/* 139 */     Manifest manifest = archive.getManifest();
/* 140 */     Attributes attributes = (manifest != null) ? manifest.getMainAttributes() : null;
/* 141 */     String location = (attributes != null) ? attributes.getValue("Spring-Boot-Classpath-Index") : null;
/* 142 */     return (location != null) ? location : (getEntryPathPrefix() + "classpath.idx");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Archive getArchive();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract String getMainClass() throws Exception;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract Set<URL> getClassPathUrls() throws Exception;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getEntryPathPrefix() {
/* 170 */     return "BOOT-INF/";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isIncludedOnClassPath(Archive.Entry entry) {
/* 180 */     return isLibraryFileOrClassesDirectory(entry);
/*     */   }
/*     */   
/*     */   protected boolean isLibraryFileOrClassesDirectory(Archive.Entry entry) {
/* 184 */     String name = entry.name();
/* 185 */     if (entry.isDirectory()) {
/* 186 */       return name.equals("BOOT-INF/classes/");
/*     */     }
/* 188 */     return name.startsWith("BOOT-INF/lib/");
/*     */   }
/*     */   
/*     */   protected boolean isIncludedOnClassPathAndNotIndexed(Archive.Entry entry) {
/* 192 */     if (!isIncludedOnClassPath(entry)) {
/* 193 */       return false;
/*     */     }
/* 195 */     return (this.classPathIndex == null || !this.classPathIndex.containsEntry(entry.name()));
/*     */   }
/*     */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\org\springframework\boot\loader\launch\Launcher.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */