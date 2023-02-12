package com.javarush.task.task19.task1907;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Считаем слово
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            String fn = console.readLine();

            try (BufferedReader br = new BufferedReader(new FileReader(fn))) {
                int count = 0;
                while (br.ready()) {
                    String s = br.readLine();
                    String[] arr = s.split("[\\p{Punct} ]+");
                    for (String s1 : arr) {
                        if (s1.equals("world")) {
                            count++;
                        }
                    }
                }
                System.out.println(count);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
