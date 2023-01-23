package com.javarush.task.pro.task15.task1516;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/* 
Файл или директория
*/

public class Solution {
    private static final String THIS_IS_FILE = " - это файл";
    private static final String THIS_IS_DIR = " - это директория";

    public static void main(String[] args) {
        try (InputStream stream = System.in;
             InputStreamReader inputStreamReader = new InputStreamReader(stream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while (true) {
                Path path = Path.of(bufferedReader.readLine());
                if (Files.isRegularFile(path))
                    System.out.println(path + THIS_IS_FILE);
                else if (Files.isDirectory(path))
                    System.out.println(path + THIS_IS_DIR);
                else
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
