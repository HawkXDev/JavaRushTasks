package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in);
             FileInputStream inputStream = new FileInputStream(scanner.nextLine())) {

            int[] bytes = new int[inputStream.available()];
            int i = -1;
            while (inputStream.available() > 0) {
                bytes[++i] = inputStream.read();
            }

            int[] newArr = Arrays.stream(bytes).distinct().toArray();
            Arrays.sort(newArr);

            for (int i1 : newArr) {
                System.out.printf("%d ", i1);
            }
        }
    }
}
