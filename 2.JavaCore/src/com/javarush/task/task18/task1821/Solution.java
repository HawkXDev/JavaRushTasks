package com.javarush.task.task18.task1821;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) {
        if (args.length == 0) return;
        String fn = args[0];

        int[] array = new int[128];

        try (FileReader fr = new FileReader(fn)) {

            while (fr.ready()) {
                int asciiValue = fr.read();
                array[asciiValue]++;
            }

            for (int i = 0; i < array.length; i++) {
                if (array[i] != 0) {
                    char c = (char) i;
                    System.out.println(c + " " + array[i]);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
