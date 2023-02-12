package com.javarush.task.task19.task1908;

import java.io.*;
import java.util.ArrayList;

/* 
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            String f1 = console.readLine();
            String f2 = console.readLine();

            try (BufferedReader br = new BufferedReader(new FileReader(f1));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(f2))) {
                while (br.ready()) {
                    String s = br.readLine();
                    String[] arr = s.split(" ");
                    for (String s1 : arr) {
                        try {
                            int i = Integer.parseInt(s1);
                            bw.write(i + " ");
                        } catch (NumberFormatException ignored) {
                        }
                    }
                }
            }
        }
    }
}
