package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
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
            if (ras.length() > number) {
                ras.seek(number);
            } else {
                ras.seek(ras.length());
            }
            ras.write(text.getBytes());
        } catch (IOException e) {
            System.out.println("File r/w error: " + e.getMessage());
        }
    }
}
