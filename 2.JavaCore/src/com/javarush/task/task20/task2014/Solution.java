package com.javarush.task.task20.task2014;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path file = Files.createTempFile("temp_file", "temp");
        System.out.println(file);

        try (
                FileInputStream fis = new FileInputStream(file.toFile());
                FileOutputStream fos = new FileOutputStream(file.toFile())
        ) {

            Solution savedObject = new Solution(-4);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(savedObject);
            outputStream.close();

            Solution loadedObject = new Solution(5);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            loadedObject = (Solution) inputStream.readObject();
            inputStream.close();

            System.out.println(savedObject.string);
            System.out.println(loadedObject.string);

        }

    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
