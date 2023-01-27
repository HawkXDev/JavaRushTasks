package com.javarush.task.task13.task1326;

import java.io.*;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/* 
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (FileInputStream fileInputStream = new FileInputStream(scanner.nextLine());
             BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            ArrayList<Integer> arr = new ArrayList<>();
            while (reader.ready()) {
                int i = Integer.parseInt(reader.readLine());
                arr.add(i);
            }
            arr.stream().filter(x -> x % 2 == 0).sorted().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
