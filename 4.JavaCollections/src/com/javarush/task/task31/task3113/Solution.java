package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* 
Что внутри папки?
*/

public class Solution extends SimpleFileVisitor<Path> {

    private final AtomicInteger totalFolders = new AtomicInteger(-1);
    private final AtomicInteger totalFiles = new AtomicInteger();
    private final AtomicLong totalSize = new AtomicLong();

    public static void main(String[] args) throws IOException {
        String folder;
        try (Scanner scanner = new Scanner(System.in)) {
            folder = scanner.nextLine();
        }

        Path path = Paths.get(folder);
        if (!Files.isDirectory(path)) {
            System.out.println(path + " - не папка");
            return;
        }

        Solution solution = new Solution();
        Files.walkFileTree(path, solution);
        System.out.println("Всего папок - " + solution.getTotalFolders());
        System.out.println("Всего файлов - " + solution.getTotalFiles());
        System.out.println("Общий размер - " + solution.getTotalSize());
    }

    public int getTotalFolders() {
        return totalFolders.get();
    }

    public int getTotalFiles() {
        return totalFiles.get();
    }

    public long getTotalSize() {
        return totalSize.get();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        totalFiles.incrementAndGet();
        totalSize.addAndGet(Files.size(file));
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        totalFolders.incrementAndGet();
        return super.preVisitDirectory(dir, attrs);
    }
}
