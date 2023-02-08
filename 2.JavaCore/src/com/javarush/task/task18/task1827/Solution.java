package com.javarush.task.task18.task1827;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Прайсы
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        if (args.length < 4) return;
        if (!args[0].equals("-c")) return;

        String productName = lenTo(args[1], 30);
        String price = lenTo(args[2], 8);
        String quantity = lenTo(args[3], 4);

        try (Scanner scanner = new Scanner(System.in)) {
            String fn = scanner.nextLine();

            long maxId = 0;
            String maxIdStr = "";

            try (FileReader fileReader = new FileReader(fn)) {

                while (fileReader.ready()) {
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    while (fileReader.ready() && sb.length() < 8) {
                        char c = (char) fileReader.read();
                        sb.append(c);
                    }
                    long id = Long.parseLong(sb.toString().trim());
                    if (id > maxId) maxId = id;

                    while (fileReader.ready() && ((char) fileReader.read()) != '\n') {
                        fileReader.read();
                    }
                }

                maxIdStr = String.valueOf(++maxId);
                maxIdStr = lenTo(maxIdStr, 8);

            }

            try (FileWriter fileWriter = new FileWriter(fn, true)) {

                String str = "\n" + maxIdStr + productName + price + quantity;
                char[] charArray = new char[str.length()];
                str.getChars(0, str.length(), charArray, 0);
                for (char c : charArray) {
                    fileWriter.write(c);
                }

            }

        }

    }

    private static String lenTo(String maxIdStr, int i) {
        if (maxIdStr.length() > i) {
            return maxIdStr.substring(0, i);
        } else {
            int j = maxIdStr.length();
            StringBuilder stringBuilder = new StringBuilder(maxIdStr);
            while (j++ < i) {
                stringBuilder.append(" ");
            }
            return stringBuilder.toString();
        }
    }

}
