package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            String fileName = scanner.nextLine();
            FileInputStream inputStream = new FileInputStream(fileName);
            int max = 0;
            while (inputStream.available() > 0) {
                int b = inputStream.read();
                if (b > max) {
                    max = b;
                }
            }
            inputStream.close();
            System.out.println(max);
        }
    }
}
