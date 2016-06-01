package Coding;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.MessageDigest;

/**
 * Created by user on 29.05.2016.
 */
public class Application {

    public static byte[] encrypt(String text, String password) throws Exception {
        byte[] cipherText = null;
        byte[] pass = MessageDigest.getInstance("MD5").digest(password.getBytes());
        Key key = new SecretKeySpec(pass, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        cipherText = cipher.doFinal(text.getBytes());
        return cipherText;
    }

    public static String decrypt(byte[] text, String password) throws Exception {
        byte[] dectyptedText = null;
        byte[] pass = MessageDigest.getInstance("MD5").digest(password.getBytes());
        Key key = new SecretKeySpec(pass,"AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        dectyptedText = cipher.doFinal(text);
        return new String(dectyptedText);
    }



    public static void main(String[] args) throws Exception {
//        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
//        keyGen.init(128);
//        SecretKey secretKey = keyGen.generateKey();
        OutputStream os = new FileOutputStream("d://result.txt") ;
        os.write(encrypt("Hello", "key"));
        os.close();
        System.out.println(decrypt((encrypt("Hello", "key")),"key"));

    }
}