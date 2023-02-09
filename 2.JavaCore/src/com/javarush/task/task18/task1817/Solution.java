package com.javarush.task.task18.task1817;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* 
Пробелы
*/

public class Solution {
    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }

        String fn = args[0];

        double countSpaces = 0;
        double countAll = 0;

        try (FileInputStream fileInputStream = new FileInputStream(fn)) {

            while (fileInputStream.available() > 0) {
                char c = (char) fileInputStream.read();
                if (c == ' ')
                    countSpaces++;
                else
                    countAll++;
            }
            double d = countSpaces / (countSpaces + countAll) * 100;
            System.out.printf("%.2f", d);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
