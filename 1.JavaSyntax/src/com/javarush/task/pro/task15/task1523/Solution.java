package com.javarush.task.pro.task15.task1523;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;

/* 
Получение информации по API
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://httpbin.org/post");
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(1);
        } catch (Exception e){ e.printStackTrace(); }
        try (InputStream inputStream = connection.getInputStream()) {
            var data = inputStream.readAllBytes();
            System.out.println(new String(data));
        } catch (Exception e){ e.printStackTrace(); }
    }
}

