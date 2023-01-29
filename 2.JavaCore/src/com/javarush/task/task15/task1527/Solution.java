package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        int i = url.indexOf("?");
        String s = url.substring(i + 1);
        String[] l = s.split("&");
        ArrayList<String> obj = new ArrayList<>();
        for (String paramLine : l) {
            String[] pair = paramLine.split("=");
            String param = pair[0];
            System.out.printf(param + " ");
            if (pair.length == 2) {
                String value = pair[1];
                if (param.equals("obj")) {
                    obj.add(value);
                }
            }
        }
        System.out.println();
        for (String value : obj) {
            try {
                double d = Double.parseDouble(value);
                alert(d);
            } catch (Exception ignored) {
                alert(value);
            }
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
