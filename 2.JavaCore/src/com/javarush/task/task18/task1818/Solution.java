package com.javarush.task.task18.task1818;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/* 
Два в одном
*/

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> files = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            files.add(scanner.nextLine());
            files.add(scanner.nextLine());
            files.add(scanner.nextLine());
        }

        try (FileInputStream fileInputStream1 = new FileInputStream(files.get(1));
             FileInputStream fileInputStream2 = new FileInputStream(files.get(2));
             FileOutputStream fileOutputStream = new FileOutputStream(files.get(0))) {

            byte[] buf = new byte[1000];

            while (fileInputStream1.available() > 0) {
                int c = fileInputStream1.read(buf);
                fileOutputStream.write(buf,0, c);
            }
            while (fileInputStream2.available() > 0) {
                int c = fileInputStream2.read(buf);
                fileOutputStream.write(buf,0, c);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
