package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        try (
                InputStream stream = System.in;
                InputStreamReader streamReader = new InputStreamReader(stream);
                BufferedReader reader = new BufferedReader(streamReader);
                OutputStream outputStream = new FileOutputStream(reader.readLine());
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        ) {
            String s = "";
            do {
                s = reader.readLine();
                bufferedWriter.write(s+"\n");
            } while (!s.equals("exit"));
        }
    }
}
