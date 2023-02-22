package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];
        ArrayList<String> list = new ArrayList<>(args.length - 1);
        list.addAll(Arrays.asList(args).subList(1, args.length));
        Collections.sort(list);

        Vector<FileInputStream> vector = new Vector<>(args.length - 1);
        for (String s : list) {
            vector.add(new FileInputStream(s));
        }

        try (ZipInputStream is = new ZipInputStream(new SequenceInputStream(vector.elements()))) {
            while (true) {
                ZipEntry entry = is.getNextEntry();
                if (entry == null) break;

                try (OutputStream os = new BufferedOutputStream(new FileOutputStream(resultFileName))) {
                    final int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];
                    for (int readBytes; (readBytes = is.read(buffer, 0, bufferSize)) > -1; ) {
                        os.write(buffer, 0, readBytes);
                    }
                    os.flush();
                }
            }
        }
    }
}
