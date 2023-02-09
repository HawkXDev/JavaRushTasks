package com.javarush.task.task18.task1822;

import java.io.*;
import java.util.Scanner;

/* 
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String fn = scanner.nextLine();
            int id = Integer.parseInt(args[0]);

            try (BufferedReader br = new BufferedReader(new FileReader(fn))) {

                while (br.ready()) {
                    String line = br.readLine();
                    int lineInt = Integer.parseInt(line.split(" ")[0]);

                    if (lineInt == id) {
                        System.out.println(line);
                        break;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
