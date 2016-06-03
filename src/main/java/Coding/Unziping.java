package Coding;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by user on 01.06.2016.
 */
public class Unziping {
    public static void unpack(String fileName) throws Exception {
        byte[] buffer = new byte[1024];
        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(fileName);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry entry = zis.getNextEntry();
        while (entry  != null) {
            String fileNameTemp = entry.getName();
            File newFile = new File("d:JavaTemp/forziptemp"+File.separator+fileNameTemp);
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            entry = zis.getNextEntry();

        }
        zis.closeEntry();
        zis.close();
    }
// not used
//    public static void write(InputStream in, OutputStream out) throws Exception {
//        byte[] buffer = new byte[1024];
//        int len;
//        while ((len=in.read(buffer))>=0) {
//            out.write(buffer,0,len);
//
//        }
//        out.close();
//        in.close();
//    }

    public static void main(String[] args) throws Exception {
        unpack("d:JavaTemp/my.zip");
    }
}
