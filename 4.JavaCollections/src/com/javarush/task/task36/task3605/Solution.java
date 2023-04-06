package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;
import java.util.regex.Pattern;

/* 
Использование TreeSet
*/

public class Solution {
    private static final Pattern PATTERN = Pattern.compile("[\\s\\p{P}]");

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            int i;
            while ((i = reader.read()) != -1) {
                sb.append((char) i);
            }
        }

        String input = PATTERN.matcher(sb.toString()).replaceAll("");
        TreeSet<Character> tree = new TreeSet<>();

        for (char c : input.toLowerCase(Locale.getDefault()).toCharArray()) {
            tree.add(c);
        }

        int count = Math.min(tree.size(), 5);
        Iterator<Character> iterator = tree.iterator();
        while (count > 0) {
            System.out.print(iterator.next());
            count--;
        }
    }
}
