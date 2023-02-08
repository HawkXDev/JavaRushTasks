package com.javarush.task.task18.task1809;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 
Реверс файла
*/

public class Solution {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in);
             FileInputStream file1 = new FileInputStream(scanner.nextLine());
             FileOutputStream file2 = new FileOutputStream(scanner.nextLine());) {

            List<Integer> collection = new ArrayList<Integer>(file1.available());
            while (file1.available() > 0) collection.add(file1.read());
            Collections.reverse(collection);
            for (Integer integer : collection) file2.write(integer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
