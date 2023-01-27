package com.javarush.task.task13.task1318;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/* 
Чтение файла
*/

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (InputStream stream = new FileInputStream(scanner.nextLine());
             InputStreamReader inputStreamReader = new InputStreamReader(stream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            while (bufferedReader.ready())
                System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}