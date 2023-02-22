package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

/* 
Находим все файлы
*/

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new LinkedList<>();
        Path rootFolder = Paths.get(root);

        Files.walkFileTree(rootFolder, new FileVisitor(list));

        return list;
    }

    static class FileVisitor extends SimpleFileVisitor<Path> {
        private final List<String> list;

        public FileVisitor(List<String> list) {
            this.list = list;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            list.add(String.valueOf(file));
            return super.visitFile(file, attrs);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getFileTree("/Users/sokol/Desktop"));
    }
}
