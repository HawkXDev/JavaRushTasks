package com.javarush.task.pro.task09.task0908;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.regex.Pattern;

/* 
Двоично-шестнадцатеричный конвертер
*/

public class Solution {

    public static void main(String[] args) {
        String binaryNumber = "100111010000";
        System.out.println("Двоичное число " + binaryNumber +
                " равно шестнадцатеричному числу "
                + toHex(binaryNumber));
        String hexNumber = "9d0";
        System.out.println("Шестнадцатеричное число " + hexNumber + " равно двоичному числу " + toBinary(hexNumber));
    }

    public static String toHex(String binaryNumber) {
        if (binaryNumber == null || binaryNumber.equals(""))
            return "";
        if (!Pattern.matches("[01]+", binaryNumber))
            return "";
        int n = binaryNumber.length() % 4;
        if (n > 0) {
            StringBuilder binaryNumberBuilder = new StringBuilder(binaryNumber);
            for (int i = 0; i < 4 - n; i++)
                binaryNumberBuilder.insert(0, "0");
            binaryNumber = binaryNumberBuilder.toString();
        }
        int i = 0;
        StringBuilder res = new StringBuilder();
        while (i < binaryNumber.length()) {
            String s = binaryNumber.substring(i, i + 4);
            switch (s) {
                case "0000" -> res.append("0");
                case "0001" -> res.append("1");
                case "0010" -> res.append("2");
                case "0011" -> res.append("3");
                case "0100" -> res.append("4");
                case "0101" -> res.append("5");
                case "0110" -> res.append("6");
                case "0111" -> res.append("7");
                case "1000" -> res.append("8");
                case "1001" -> res.append("9");
                case "1010" -> res.append("a");
                case "1011" -> res.append("b");
                case "1100" -> res.append("c");
                case "1101" -> res.append("d");
                case "1110" -> res.append("e");
                case "1111" -> res.append("f");
            }
            i += 4;
        }
        return res.toString();
    }

    public static String toBinary(String hexNumber) {
        if (hexNumber == null || hexNumber.equals(""))
            return "";
        if (!Pattern.matches("[0123456789abcdef]+", hexNumber))
            return "";
        StringBuilder res = new StringBuilder();
        for (char c : hexNumber.toCharArray()) {
            switch (c) {
                case '0':
                    res.append("0000");
                    break;
                case '1':
                    res.append("0001");
                    break;
                case '2':
                    res.append("0010");
                    break;
                case '3':
                    res.append("0011");
                    break;
                case '4':
                    res.append("0100");
                    break;
                case '5':
                    res.append("0101");
                    break;
                case '6':
                    res.append("0110");
                    break;
                case '7':
                    res.append("0111");
                    break;
                case '8':
                    res.append("1000");
                    break;
                case '9':
                    res.append("1001");
                    break;
                case 'a':
                    res.append("1010");
                    break;
                case 'b':
                    res.append("1011");
                    break;
                case 'c':
                    res.append("1100");
                    break;
                case 'd':
                    res.append("1101");
                    break;
                case 'e':
                    res.append("1110");
                case 'f':
                    res.append("1111");
            }
        }
        return res.toString();
    }
}
