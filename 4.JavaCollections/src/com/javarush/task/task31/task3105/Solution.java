package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String zipName = args[1];

        Path path = Paths.get(fileName);
        String fileNameZip = "new/" + path.getFileName();
        Map<String, byte[]> archive = new HashMap<>();

        InputStream inputStream = new FileInputStream(zipName);
        ZipInputStream zis = new ZipInputStream(inputStream);
        ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null) {
            if (!zipEntry.isDirectory()) {
                String name = zipEntry.getName();
                if (!name.equals(fileNameZip)) {
                    byte[] bytes = readEntry(zis);
                    archive.put(name, bytes);
                }
            }
            zis.closeEntry();
        }
        zis.close();

        OutputStream outputStream = new FileOutputStream(zipName);
        ZipOutputStream zos = new ZipOutputStream(outputStream);

        zipEntry = new ZipEntry(fileNameZip);
        zos.putNextEntry(zipEntry);
        Files.copy(path, zos);
        zos.closeEntry();

        for (Map.Entry<String, byte[]> entry : archive.entrySet()) {
            zipEntry = new ZipEntry(entry.getKey());
            zos.putNextEntry(zipEntry);
            zos.write(entry.getValue());
            zos.closeEntry();
        }
        zos.close();
    }

    private static byte[] readEntry(ZipInputStream zis) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        while ((length = zis.read(buffer)) > 0) {
            bos.write(buffer, 0, length);
        }

        return bos.toByteArray();
    }
}
