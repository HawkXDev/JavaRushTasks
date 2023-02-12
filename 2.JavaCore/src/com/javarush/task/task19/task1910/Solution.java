package com.javarush.task.task19.task1910;

import java.io.*;
import java.util.ArrayList;

/* 
Пунктуация
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String f1 = br.readLine();
            String f2 = br.readLine();

            try (BufferedReader br1 = new BufferedReader(new FileReader(f1));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(f2))) {


                while (br1.ready()) {
                    String s = br1.readLine();
                    s = s.replaceAll("[\\n\\p{Punct}]+", "");
                    bw.write(s);
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
