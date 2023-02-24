package com.javarush.task.task31.task3109;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections\\src\\com\\javarush\\task\\task31\\task3109\\properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        if (fileName.contains("\\"))
            fileName = String.join(File.separator, fileName.split("\\\\"));

        Path filePath = Paths.get(fileName);
        Properties prop = new Properties();

        String ext = fileName.contains(".")
                ? fileName.substring(fileName.lastIndexOf(".") + 1)
                : "";

        try (InputStream inputStream = new FileInputStream(fileName)) {
            if (!ext.isEmpty()) {
                if (ext.equals("xml")) {
                    prop.loadFromXML(inputStream);
                } else {
                    prop.load(inputStream);
                }
            } else if (Files.isRegularFile(filePath)) {
                prop.load(inputStream);
            }
        } catch (IOException ignore) {
        }

        return prop;
    }
}
