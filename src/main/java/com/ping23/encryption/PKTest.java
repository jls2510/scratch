package com.ping23.encryption;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class PKTest {

    private static final String token1 =
        "ycRpalT2fAInlgUQ3FwqWsWwWnZbCGne85YeHNDrPTi4BP7EPpd\\/LqrylF2LSDsE4mEjhSJRwGmYzjKb+xTHAgsQthNrkN\\/q7lMCko9CJ3ZKQnjdm1D6wBRnQ16j+RvpOrOaq72OYQ9Aw1YQMZjZdNrozgmHysyw5CSANf1GEz3ok10wCLBx9zLk2Z4Ded+JpTEeGX2FrOR9+AXHIKl32ANHLqEQAt107CnYCcYmAXtLZ9sjTRB9BpzzWBebwkyhu+RUHfqKkJX1O9cFNrCdjDC3eA+Dd8oC37feCGueNZ5a4QLtpG0ulzQFYVi\\/XkO6sZBA0RblIRrabyI+4VgwDw==";
    private static final String token2 =
        "oIU7WTWWgDAJ0Rtx2eOZ\\/C9wLWVqxaMqon\\/RrnczWoRUSUahcHFtyZ9nd2vlMMvLZF2JYB64kAjIk6mbA3Nq81Jxn6pAJlpaGVQ28jgrLnLZiR\\/vFQJWpt+Qp3hIH0rqge0xgozmoX0nxDN71p26Aq\\/6joZQJCP26NoQsfI39r1W+9cDw2R50+ubYZB9ZKn0q+60tTH8SvPS7ndXmC9YE4ZMaGzPXYDue0+0VKmUfqT2vRj3XXShbjw5XHK9dmAXAt7FMPJPFagNhxZTefYPXYP2bVO\\/RTLxGFQb74qVr8+3ImnzO5DPYMGCk26QxXeLDSeRckeD0OXz\\/3nYaylHEg==";
    private static final String token3 =
        "t8E/Vjzd0rs4tZdBeumrIcEp8TnOCZdnhRiaYicclS+kWx1hlsRjaLLzdPIWouX9p36xsN9UcZPAYe01v6J5+YVKYBJoKd4nlMdEa7m72N7ayMVl4LNyd6sxq20ZKEzp06AwSilPivOYTaJrd9pRUNFTa2mF9ukKhqtzqCme+38LCUewX5onOTarnSfKHmk9Xw90ESx/WUubRAYmOqn5bu19ky2V+Fxx4SFaBGiXEDtTKgHdU1BQQUgKoEUSWWLiCMAwQtjeqdmNu7s4Zy2o5JkQdCFUzk6l7jbJb2B5ZPHbtCn05TWicwytoni+PrKjggy+d8ibP08ng0b3th2jKA==";

    public static void main(String[] args) throws Exception {

        String filename = "src/main/resources/win_public_key.txt";

        Security.addProvider(new BouncyCastleProvider());
        System.out.println("BouncyCastle provider added.");

        KeyFactory factory = KeyFactory.getInstance("RSA", "BC");
        try {
            PublicKey publicKey = generatePublicKey(factory, filename);
            System.out.println(
                String.format("Instantiated public key: %s", publicKey));
            String decrypted_msg = decryptText(token2, publicKey);
            System.out.println("Decrypted Message: " + decrypted_msg);
        }
        catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }

    private static PublicKey generatePublicKey(KeyFactory factory,
        String filename)
        throws InvalidKeySpecException, FileNotFoundException, IOException {
        PemFileReader pemFileReader = new PemFileReader(filename);
        byte[] content = pemFileReader.getPemObject().getContent();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
        return factory.generatePublic(pubKeySpec);
    }

    public static String decryptText(String msg, PublicKey key)
        throws InvalidKeyException, UnsupportedEncodingException,
        IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException,
        NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA");

        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
    }

}
