/*     */ package org.springframework.boot.loader.nio.file;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.nio.file.ClosedFileSystemException;
/*     */ import java.nio.file.FileStore;
/*     */ import java.nio.file.FileSystem;
/*     */ import java.nio.file.FileSystemNotFoundException;
/*     */ import java.nio.file.FileSystems;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.PathMatcher;
/*     */ import java.nio.file.WatchService;
/*     */ import java.nio.file.attribute.UserPrincipalLookupService;
/*     */ import java.nio.file.spi.FileSystemProvider;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
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
/*     */ class NestedFileSystem
/*     */   extends FileSystem
/*     */ {
/*  46 */   private static final Set<String> SUPPORTED_FILE_ATTRIBUTE_VIEWS = Set.of("basic");
/*     */   
/*  48 */   private static final String FILE_SYSTEMS_CLASS_NAME = FileSystems.class.getName();
/*     */   
/*  50 */   private static final Object EXISTING_FILE_SYSTEM = new Object();
/*     */   
/*     */   private final NestedFileSystemProvider provider;
/*     */   
/*     */   private final Path jarPath;
/*     */   
/*     */   private volatile boolean closed;
/*     */   
/*  58 */   private final Map<String, Object> zipFileSystems = new HashMap<>();
/*     */   
/*     */   NestedFileSystem(NestedFileSystemProvider provider, Path jarPath) {
/*  61 */     if (provider == null || jarPath == null) {
/*  62 */       throw new IllegalArgumentException("Provider and JarPath must not be null");
/*     */     }
/*  64 */     this.provider = provider;
/*  65 */     this.jarPath = jarPath;
/*     */   }
/*     */   
/*     */   void installZipFileSystemIfNecessary(String nestedEntryName) {
/*     */     try {
/*     */       boolean seen;
/*  71 */       synchronized (this.zipFileSystems) {
/*  72 */         seen = (this.zipFileSystems.putIfAbsent(nestedEntryName, EXISTING_FILE_SYSTEM) != null);
/*     */       } 
/*  74 */       if (!seen) {
/*  75 */         URI uri = new URI("jar:nested:" + this.jarPath.toUri().getPath() + "/!" + nestedEntryName);
/*  76 */         if (!hasFileSystem(uri)) {
/*  77 */           FileSystem zipFileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap());
/*  78 */           synchronized (this.zipFileSystems) {
/*  79 */             this.zipFileSystems.put(nestedEntryName, zipFileSystem);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/*  84 */     } catch (Exception seen) {
/*     */       Exception exception;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean hasFileSystem(URI uri) {
/*     */     try {
/*  91 */       FileSystems.getFileSystem(uri);
/*  92 */       return true;
/*     */     }
/*  94 */     catch (FileSystemNotFoundException ex) {
/*  95 */       return isCreatingNewFileSystem();
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isCreatingNewFileSystem() {
/* 100 */     StackTraceElement[] stack = Thread.currentThread().getStackTrace();
/* 101 */     for (StackTraceElement element : stack) {
/* 102 */       if (FILE_SYSTEMS_CLASS_NAME.equals(element.getClassName())) {
/* 103 */         return "newFileSystem".equals(element.getMethodName());
/*     */       }
/*     */     } 
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public FileSystemProvider provider() {
/* 111 */     return this.provider;
/*     */   }
/*     */   
/*     */   Path getJarPath() {
/* 115 */     return this.jarPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 120 */     if (this.closed) {
/*     */       return;
/*     */     }
/* 123 */     this.closed = true;
/* 124 */     synchronized (this.zipFileSystems) {
/*     */ 
/*     */       
/* 127 */       Objects.requireNonNull(FileSystem.class);
/* 128 */       Objects.requireNonNull(FileSystem.class); this.zipFileSystems.values().stream().filter(FileSystem.class::isInstance).map(FileSystem.class::cast)
/* 129 */         .forEach(this::closeZipFileSystem);
/*     */     } 
/* 131 */     this.provider.removeFileSystem(this);
/*     */   }
/*     */   
/*     */   private void closeZipFileSystem(FileSystem zipFileSystem) {
/*     */     try {
/* 136 */       zipFileSystem.close();
/*     */     }
/* 138 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/* 145 */     return !this.closed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSeparator() {
/* 155 */     return "/!";
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterable<Path> getRootDirectories() {
/* 160 */     assertNotClosed();
/* 161 */     return Collections.emptySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterable<FileStore> getFileStores() {
/* 166 */     assertNotClosed();
/* 167 */     return Collections.emptySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> supportedFileAttributeViews() {
/* 172 */     assertNotClosed();
/* 173 */     return SUPPORTED_FILE_ATTRIBUTE_VIEWS;
/*     */   }
/*     */ 
/*     */   
/*     */   public Path getPath(String first, String... more) {
/* 178 */     assertNotClosed();
/* 179 */     if (more.length != 0) {
/* 180 */       throw new IllegalArgumentException("Nested paths must contain a single element");
/*     */     }
/* 182 */     return new NestedPath(this, first);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathMatcher getPathMatcher(String syntaxAndPattern) {
/* 187 */     throw new UnsupportedOperationException("Nested paths do not support path matchers");
/*     */   }
/*     */ 
/*     */   
/*     */   public UserPrincipalLookupService getUserPrincipalLookupService() {
/* 192 */     throw new UnsupportedOperationException("Nested paths do not have a user principal lookup service");
/*     */   }
/*     */ 
/*     */   
/*     */   public WatchService newWatchService() throws IOException {
/* 197 */     throw new UnsupportedOperationException("Nested paths do not support the WatchService");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 202 */     if (this == obj) {
/* 203 */       return true;
/*     */     }
/* 205 */     if (obj == null || getClass() != obj.getClass()) {
/* 206 */       return false;
/*     */     }
/* 208 */     NestedFileSystem other = (NestedFileSystem)obj;
/* 209 */     return this.jarPath.equals(other.jarPath);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 214 */     return this.jarPath.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 219 */     return this.jarPath.toAbsolutePath().toString();
/*     */   }
/*     */   
/*     */   private void assertNotClosed() {
/* 223 */     if (this.closed)
/* 224 */       throw new ClosedFileSystemException(); 
/*     */   }
/*     */ }


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\org\springframework\boot\loader\nio\file\NestedFileSystem.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */