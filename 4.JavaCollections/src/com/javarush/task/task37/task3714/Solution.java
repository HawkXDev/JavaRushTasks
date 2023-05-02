package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {
    private static final Map<Character, Integer> map = new HashMap<>();

    static {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        String inverted = new StringBuilder(s).reverse().toString();
        Iterator<Integer> iterator = inverted.chars().iterator();
        int res = 0;
        int val = 0;
        int prev;
        while (iterator.hasNext()) {
            prev = val;
            val = map.get((char) iterator.next().intValue());
            if (val < prev) {
                res -= val;
            } else {
                res += val;
            }
        }
        return res;
    }
}
