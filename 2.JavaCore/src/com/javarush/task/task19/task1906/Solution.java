package com.javarush.task.task19.task1906;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/* 
Четные символы
*/

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String f1 = br.readLine();
            String f2 = br.readLine();

            try (FileReader fr = new FileReader(f1);
                 FileWriter fw = new FileWriter(f2)) {

                while (fr.ready()) {
                    int r = fr.read();
                    r = fr.read();
                    fw.write(r);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
