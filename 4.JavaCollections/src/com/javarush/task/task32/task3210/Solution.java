package com.javarush.task.task32.task3210;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        String filename, text;
        long number;
        try {
            filename = args[0];
            number = Long.parseLong(args[1]);
            text = args[2];
        } catch (RuntimeException e) {
            System.out.println("Parameters error: " + e.getMessage());
            return;
        }

        try (RandomAccessFile ras = new RandomAccessFile(filename, "rw")) {
            int l = text.length();
            byte[] textBytes = new byte[l];
            ras.seek(number);
            ras.read(textBytes, 0, l);
            String s = new String(textBytes);
            String res = s.equals(text) ? "true" : "false";
            ras.seek(ras.length());
            ras.write(res.getBytes());
        } catch (IOException e) {
            System.out.println("Read/write file error: " + e.getMessage());
        }
    }
}
