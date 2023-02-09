package com.javarush.task.task18.task1819;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/* 
Объединение файлов
*/

public class Solution {
    public static class MyBufferedInputStream extends BufferedInputStream {
        private final FileInputStream fis;

        public MyBufferedInputStream(InputStream in) {
            super(in);
            fis = (FileInputStream) in;
        }

        public byte[] readAllBytes() throws IOException {
            ArrayList<Byte> data = new ArrayList<>();
            while (fis.available() > 0) {
                data.add((byte) fis.read());
            }
            byte[] array = new byte[data.size()];
            for (int i = 0; i < data.size(); i++) {
                array[i] = data.get(i);
            }
            return array;
        }
    }

    public static void main(String[] args) {
        String fn1, fn2;

        try (Scanner scanner = new Scanner(System.in)) {
            fn1 = scanner.nextLine();
            fn2 = scanner.nextLine();
        }

        byte[] fdata1, fdata2;

        try (MyBufferedInputStream bis1 = new MyBufferedInputStream(new FileInputStream(fn1));
             MyBufferedInputStream bis2 = new MyBufferedInputStream(new FileInputStream(fn2))) {
            fdata1 = bis1.readAllBytes();
            fdata2 = bis2.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fn1))) {
            bos.write(fdata2);
            bos.write(fdata1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
