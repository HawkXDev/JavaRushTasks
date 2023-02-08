package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String filename = reader.readLine();
            try (FileInputStream inputStream = new FileInputStream(filename)) {
                int min = Integer.MAX_VALUE;
                while (inputStream.available() > 0) {
                    int buf = inputStream.read();
                    if (buf < min) min = buf;
                }
                System.out.println(min);
            }
        }
    }
}
