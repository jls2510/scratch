package com.ping23.encryption;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
 
import org.bouncycastle.jce.provider.BouncyCastleProvider;
 
public class PemReadMain {
 
    
    public final static String RESOURCES_DIR = "src/main/resources/rsa-sample/";
 
    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        System.out.println("BouncyCastle provider added.");
 
        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
        try {
            PrivateKey priv = generatePrivateKey(factory, RESOURCES_DIR + "id_rsa");
            System.out.println(String.format("Instantiated private key: %s", priv));
            
            //PublicKey pub = generatePublicKey(factory, RESOURCES_DIR + "id_rsa.pub");
            PublicKey pub = generatePublicKey(factory, RESOURCES_DIR + "win_public_key.txt");
            System.out.println(String.format("Instantiated public key: %s", pub));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
 
    private static PrivateKey generatePrivateKey(KeyFactory factory, String filename) throws InvalidKeySpecException, FileNotFoundException, IOException {
        PemFileReader pemFileReader = new PemFileReader(filename);
        byte[] content = pemFileReader.getPemObject().getContent();
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
        return factory.generatePrivate(privKeySpec);
    }
    
    private static PublicKey generatePublicKey(KeyFactory factory, String filename) throws InvalidKeySpecException, FileNotFoundException, IOException {
        PemFileReader pemFileReader = new PemFileReader(filename);
        byte[] content = pemFileReader.getPemObject().getContent();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
        return factory.generatePublic(pubKeySpec);
    }
}
