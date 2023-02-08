package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in);
             FileInputStream inputStream = new FileInputStream(scanner.nextLine())) {

            List<Byte> list = new ArrayList<>(inputStream.available());
            while (inputStream.available() > 0) {
                list.add((byte) inputStream.read());
            }

            int maxCount = 0;
            for (Byte aByte : list) {
                int max = (int) list.stream().filter(x -> x.equals(aByte)).count();
                if (max > maxCount) maxCount = max;
            }
            int finalMaxCount = maxCount;
            list.stream().distinct().forEach(x -> {
                int count = (int) list.stream().filter(j -> j.equals(x)).count();
                if (count == finalMaxCount) {
                    System.out.printf(x + " ");
                }
            });

        }
    }
}
