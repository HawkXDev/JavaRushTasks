package com.javarush.task.task18.task1820;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) {
        String f1, f2;
        try (Scanner scanner = new Scanner(System.in)) {
            f1 = scanner.nextLine();
            f2 = scanner.nextLine();
        }

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(f1));
             OutputStreamWriter osr = new OutputStreamWriter(new FileOutputStream(f2))) {

            ArrayList<Integer> array = new ArrayList<>();

            while (isr.ready()) {
                StringBuilder sb = new StringBuilder();

                while (isr.ready()) {
                    char c = (char) isr.read();
                    if (Character.isDigit(c) || (c == '-') || (c == '.'))
                        sb.append(c);
                    else break;
                }

                int i = Math.round(Float.parseFloat(sb.toString()));
                array.add(i);
            }

            for (Integer i : array) {
                osr.write(i + " ");
            }

            osr.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
