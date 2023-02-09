package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.*;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String fn;
            while (!(fn = scanner.nextLine()).equals("exit")) {
                ReadThread readThread = new ReadThread(fn);
                readThread.start();
            }
        }

    }

    public static class ReadThread extends Thread {
        private final String fn;

        public ReadThread(String fileName) {
            fn = fileName;
        }

        @Override
        public void run() {
            try (FileReader fr = new FileReader(fn)) {
                byte[] bytes = new byte[256];

                while (fr.ready()) {
                    int byteI = fr.read();
                    bytes[byteI]++;
                }

                TreeSet<Byte> tree = new TreeSet<>();
                for (byte aByte : bytes) {
                    tree.add(aByte);
                }

                int max = tree.last();

                for (int i = 0; i < bytes.length; i++) {
                    if (bytes[i] == max) {
                        resultMap.put(fn, i);
                        break;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
