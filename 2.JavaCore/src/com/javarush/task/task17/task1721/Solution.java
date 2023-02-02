package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws FileNotFoundException, CorruptedDataException {

        try (Scanner scanner = new Scanner(System.in);
             BufferedReader reader1 = new BufferedReader(new FileReader(scanner.nextLine()));
             BufferedReader reader2 = new BufferedReader(new FileReader(scanner.nextLine()))) {

            while (reader1.ready()) {
                allLines.add(reader1.readLine());
            }
            while (reader2.ready()) {
                forRemoveLines.add(reader2.readLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        new Solution().joinData();
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
