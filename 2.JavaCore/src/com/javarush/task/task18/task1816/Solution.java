package com.javarush.task.task18.task1816;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
Английские буквы
*/

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 0) return;

        String fn = args[0];

        try (FileReader fileReader = new FileReader(fn)) {

            int count = 0;
            while (fileReader.ready()) {
                char c = (char) fileReader.read();
                if (Character.isLetter(c)) {
                    if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                        count++;
                    }
                }
            }

            System.out.println(count);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
