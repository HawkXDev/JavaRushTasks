package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            while (br.ready()) {
                lines.add(br.readLine());
            }
        }

        TreeMap<String, Double> map = new TreeMap<>();

        for (String line : lines) {
            String[] nameValue = line.split(" ");
            String name = nameValue[0];
            double value = Double.parseDouble(nameValue[1]);
            if (map.containsKey(name)) {
                map.merge(name, value, Double::sum);
            } else {
                map.put(name, value);
            }
        }

        for (Map.Entry<String, Double> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
