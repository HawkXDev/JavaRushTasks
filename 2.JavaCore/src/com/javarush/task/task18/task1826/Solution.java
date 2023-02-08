package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        try (FileInputStream fileInputStream = new FileInputStream(args[1]);
             FileOutputStream fileOutputStream = new FileOutputStream(args[2])) {

            List<Integer> list = new ArrayList<>(fileInputStream.available());
            while (fileInputStream.available() > 0) {
                list.add(fileInputStream.read());
            }

            Collections.reverse(list);

            for (Integer i : list) {
                fileOutputStream.write(i);
            }

        }

    }

}
