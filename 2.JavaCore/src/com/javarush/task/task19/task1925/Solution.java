package com.javarush.task.task19.task1925;

import java.io.*;
import java.util.ArrayList;

/* 
Длинные слова
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]))) {

            ArrayList<String> words = new ArrayList<>();
            while (reader.ready()) {
                String[] split = reader.readLine().split(" ");
                for (String s : split) {
                    if (s.length() > 6) {
                        words.add(s);
                    }
                }
            }

            for (int i = 0; i < words.size(); i++) {
                writer.write(words.get(i));
                if (i < words.size() - 1) {
                    writer.write(",");
                }
            }

        }
    }
}
