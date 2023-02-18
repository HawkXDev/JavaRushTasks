package com.javarush.task.task20.task2022;

import java.io.*;
import java.nio.file.Files;

/* 
Переопределение сериализации в потоке
*/

public class Solution implements Serializable, AutoCloseable {
    public static void main(String[] args) throws Exception {
        String filepath = Files.createTempFile("temp", "tmp").toString();
        System.out.println(filepath);
        com.javarush.task.task20.task2022.Solution solution = new com.javarush.task.task20.task2022.Solution(filepath);
        solution.writeObject("Hello");
        ByteArrayOutputStream barray = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(barray);
        out.writeObject(solution);
        solution.close();
        out.close();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(barray.toByteArray());
        ObjectInputStream is = new ObjectInputStream(inputStream);
        com.javarush.task.task20.task2022.Solution newSolution = (com.javarush.task.task20.task2022.Solution) is.readObject();
        newSolution.writeObject("World");
        is.close();
        newSolution.close();
    }
    
    transient private FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws Exception {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }
}
