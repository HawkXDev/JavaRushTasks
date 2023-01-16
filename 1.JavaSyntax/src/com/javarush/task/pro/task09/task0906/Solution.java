package com.javarush.task.pro.task09.task0906;

import java.util.regex.Pattern;

/* 
Двоичный конвертер
*/

public class Solution {
    public static void main(String[] args) {
        int decimalNumber = Integer.MAX_VALUE;
        System.out.println("Десятичное число " + decimalNumber + " равно двоичному числу " + toBinary(decimalNumber));
        String binaryNumber = "1111111111111111111111111111111";
        System.out.println("Двоичное число " + binaryNumber + " равно десятичному числу " + toDecimal(binaryNumber));
    }

    public static String toBinary(int decimalNumber) {
        if (decimalNumber <= 0)
            return "";
        String s = "";
        while (decimalNumber != 0) {
            s = decimalNumber % 2 + s;
            decimalNumber = decimalNumber / 2;
        }
        return s;
    }

    public static int toDecimal(String binaryNumber) {
        if (binaryNumber == null || binaryNumber == "")
            return 0;
        int i = 0;
        char[] arr = binaryNumber.toCharArray();
        for (int j = 0; j < binaryNumber.length(); j++) {
            char c = arr[arr.length - j - 1];
            if (c == '1')
                i += Math.pow(2, j);
        }
        return i;
    }
}
