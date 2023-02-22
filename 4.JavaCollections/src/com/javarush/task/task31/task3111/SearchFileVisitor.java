package com.javarush.task.task31.task3111;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private boolean isSetUpPartOfName;
    private String partOfContent;
    private boolean isSetUpPartOfContent;
    private int minSize;
    private boolean isSetUpMinSize;
    private int maxSize;
    private boolean isSetUpMaxSize;
    private final List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file);

        // размер файла: content.length
        if ((isSetUpMinSize && content.length < minSize)
                || (isSetUpMaxSize && content.length > maxSize)) {
            return FileVisitResult.CONTINUE;
        }

        if (isSetUpPartOfName && !file.getFileName().toString().contains(partOfName)) {
            return FileVisitResult.CONTINUE;
        }

        if (isSetUpPartOfContent && !(new String(content).contains(partOfContent))) {
            return FileVisitResult.CONTINUE;
        }

        foundFiles.add(file);
        return super.visitFile(file, attrs);
    }

    public void setPartOfName(String amigo) {
        this.partOfName = amigo;
        this.isSetUpPartOfName = true;
    }

    public void setPartOfContent(String programmer) {
        this.partOfContent = programmer;
        this.isSetUpPartOfContent = true;
    }

    public void setMinSize(int i) {
        this.minSize = i;
        this.isSetUpMinSize = true;
    }

    public void setMaxSize(int i) {
        this.maxSize = i;
        this.isSetUpMaxSize = true;
    }

    public List<Path> getFoundFiles() {
        return this.foundFiles;
    }

}
