package com.javarush.task.task18.task1810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
DownloadException
*/

public class Solution {
    public static void main(String[] args) throws DownloadException {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String fileName = scanner.nextLine();
                try (FileInputStream inputStream = new FileInputStream(fileName)) {
                    if (inputStream.available() < 1000) throw new DownloadException();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class DownloadException extends Exception {

    }
}
