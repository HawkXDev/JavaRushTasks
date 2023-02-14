package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();
        reader.close();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            while (br.ready()) {
                String line = br.readLine();

                int count = 0;

                for (String word : words) {
                    Pattern pattern = Pattern.compile("\\b" + word + "\\b", Pattern.UNICODE_CHARACTER_CLASS);
                    Matcher matcher = pattern.matcher(line);

                    while (matcher.find())
                        count++;
                }

                if (count == 2) {
                    System.out.println(line);
                }

            }
        }
    }
}
