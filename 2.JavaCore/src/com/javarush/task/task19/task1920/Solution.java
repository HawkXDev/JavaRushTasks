package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Самый богатый
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fn = args[0];

        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fn))) {
            while (br.ready())
                lines.add(br.readLine());
        }

        TreeMap<String, Double> map = new TreeMap<>();

        for (String line : lines) {
            String[] split = line.split(" ");
            String key = split[0];
            double value = Double.parseDouble(split[1]);

            if (map.containsKey(key)) {
                map.merge(key, value, Double::sum);
            } else {
                map.put(key, value);
            }
        }

        double max = map.values().stream().max(Double::compare).get();

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                System.out.println(entry.getKey());
            }
        }
    }
}
