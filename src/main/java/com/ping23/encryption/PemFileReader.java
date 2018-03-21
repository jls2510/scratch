package com.ping23.encryption;

    import java.io.FileInputStream;
    import java.io.FileNotFoundException;
    import java.io.IOException;
    import java.io.InputStreamReader;
     
    import org.bouncycastle.util.io.pem.PemObject;
    import org.bouncycastle.util.io.pem.PemReader;
     
    public class PemFileReader {
     
        private PemObject pemObject;
     
        public PemFileReader(String filename) throws FileNotFoundException, IOException {
            PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)));
            try {
                this.pemObject = pemReader.readPemObject();
            } finally {
                pemReader.close();
            }
        }
     
        public PemObject getPemObject() {
            return pemObject;
        }
    }