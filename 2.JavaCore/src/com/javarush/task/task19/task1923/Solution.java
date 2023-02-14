package com.javarush.task.task19.task1923;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {

            while (reader.ready()) {
                String line = reader.readLine();
                String[] words = line.split(" ");

                Pattern pattern = Pattern.compile("\\d", Pattern.UNICODE_CHARACTER_CLASS);
                for (String word : words) {
                    Matcher matcher = pattern.matcher(word);
                    if (matcher.find()) {
                        writer.write(word + " ");
                    }
                }
            }

        }
    }
}
