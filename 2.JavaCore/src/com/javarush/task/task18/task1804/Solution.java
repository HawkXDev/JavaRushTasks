package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/* 
Самые редкие байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in);
             FileInputStream inputStream = new FileInputStream(scanner.nextLine())) {

            int[] byteCountArray = new int[256];
            while (inputStream.available() > 0) {
                byteCountArray[inputStream.read()] += 1;
            }

            int minCount = Integer.MAX_VALUE;
            for (int byteCount : byteCountArray) {
                if (byteCount > 0 && byteCount < minCount) minCount = byteCount;
            }

            ArrayList<Integer> resultList = new ArrayList<>();
            for (int i = 0; i < byteCountArray.length; i++) {
                if (byteCountArray[i] == minCount) resultList.add(i);
            }
            for (Integer resultItem : resultList) System.out.print(resultItem + " ");

        }
    }

}
