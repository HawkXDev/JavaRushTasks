package com.javarush.task.task33.task3310.strategy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(Integer.toHexString(hashCode()), ".tmp");
            path.toFile().deleteOnExit();

            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void putEntry(Entry entry) {
        try (OutputStream os = Files.newOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Entry getEntry() {
        if (getFileSize() > 0) {
            try (InputStream is = Files.newInputStream(path);
                 ObjectInputStream ois = new ObjectInputStream(is)) {
                return (Entry) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
