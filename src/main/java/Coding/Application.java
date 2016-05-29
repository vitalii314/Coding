package Coding;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by user on 29.05.2016.
 */
public class Application {

    public static String decrypt(byte[] text, SecretKey key) throws Exception {
        byte[] dectyptedText = null;

        final Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        dectyptedText = cipher.doFinal(text);


        return new String(dectyptedText);
    }

    public static byte[] encrypt(String text, SecretKey key) throws Exception {
        byte[] cipherText = null;

        final Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        cipherText = cipher.doFinal(text.getBytes());

        return cipherText;
    }

    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        OutputStream os = new FileOutputStream("d://result.txt") ;
        os.write(encrypt("Hello", secretKey));
        os.close();
        System.out.println(decrypt((encrypt("Hello", secretKey)),secretKey));

    }
}


