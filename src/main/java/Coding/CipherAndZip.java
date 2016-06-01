package Coding;

/**
 * Created by user on 01.06.2016.
 */

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.MessageDigest;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;


public class CipherAndZip {


    public byte[] encrypt(String text, String password, String fileName) throws Exception {
        byte[] cipherText = null;
        byte[] pass = MessageDigest.getInstance("MD5").digest(password.getBytes());
        Key key = new SecretKeySpec(pass, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        GZIPOutputStream zip = new GZIPOutputStream(new CipherOutputStream(new FileOutputStream(fileName), cipher));
        ZipEntry zentry = new ZipEntry(fileName);
        zip.write(text.getBytes("UTF-8"));
        zip.flush();
        zip.close();

        cipherText = cipher.doFinal(text.getBytes());
        return cipherText;
    }

    public String decrypt(String fileName, String password) throws Exception {
        byte[] pass = MessageDigest.getInstance("MD5").digest(password.getBytes());
        Key key = new SecretKeySpec(pass, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        GZIPInputStream zis = new GZIPInputStream(new CipherInputStream(new FileInputStream(fileName),cipher));
        BufferedReader br = new BufferedReader(new InputStreamReader(zis,"UTF-8"));
        StringBuffer result = new StringBuffer();
        String s=null;
        while ((s = br.readLine()) != null) {
            result.append(s);
        }
        br.close();
        return result.toString();

    }


    public static void main(String[] args) throws Exception {

        CipherAndZip app = new CipherAndZip();
        app.encrypt("Hello", "key","d:fileforshifr.txt");
        System.out.println(app.decrypt("d:fileforshifr.txt", "key"));

    }
}


