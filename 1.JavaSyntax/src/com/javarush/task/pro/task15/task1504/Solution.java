package com.javarush.task.pro.task15.task1504;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/* 
Перепутанные байты
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             InputStream source = Files.newInputStream(Path.of(reader.readLine()));
             OutputStream dest = Files.newOutputStream(Path.of(reader.readLine()));
        ) {
            while (source.available() > 0) {
                int b1 = source.read();
                if (source.available() > 0) {
                    int b2 = source.read();
                    dest.write(b2);
                }
                dest.write(b1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

