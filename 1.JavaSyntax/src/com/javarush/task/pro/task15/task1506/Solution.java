package com.javarush.task.pro.task15.task1506;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/* 
Фейсконтроль
*/

public class Solution {
    public static void main(String[] args) {
        try (Scanner console = new Scanner(System.in)) {
            var lines = Files.readAllLines(Path.of(console.nextLine()));
            for (var s : lines) {
                s = s.replaceAll("[., ]", "");
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

