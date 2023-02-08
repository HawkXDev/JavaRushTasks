package com.javarush.task.task18.task1808;

import java.io.*;
import java.util.Scanner;

/* 
Разделение файла
*/

public class Solution {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);
             FileInputStream inputStream = new FileInputStream(scanner.nextLine());
             FileOutputStream outputStream1 = new FileOutputStream(scanner.nextLine());
             FileOutputStream outputStream2 = new FileOutputStream(scanner.nextLine());) {

            int total = inputStream.available();
            int count1;
            if (total % 2 == 0)
                count1 = total / 2;
            else
                count1 = total / 2 + 1;

            while (inputStream.available() > total - count1) {
                outputStream1.write(inputStream.read());
            }

            while (inputStream.available() > 0) {
                outputStream2.write(inputStream.read());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
