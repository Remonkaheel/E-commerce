package org.springframework.boot.loader.net.protocol.jar;

import java.io.IOException;
import java.util.jar.Manifest;

@FunctionalInterface
interface ManifestSupplier {
  Manifest getManifest() throws IOException;
}


/* Location:              C:\Users\oremo\Downloads\e-commerce.war!\org\springframework\boot\loader\net\protocol\jar\UrlJarManifest$ManifestSupplier.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */