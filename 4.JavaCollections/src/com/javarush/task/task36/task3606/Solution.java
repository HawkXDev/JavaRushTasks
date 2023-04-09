package com.javarush.task.task36.task3606;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* 
Осваиваем ClassLoader и Reflection
*/

public class Solution {
    private final List<Class<?>> hiddenClasses = new ArrayList<>();
    private final String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(
                Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                        + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        File folder = new File(packageName);

        for (String fileName : Objects.requireNonNull(folder.list())) {
            if (fileName.endsWith(".class")) {
                String className = packageName.replaceAll("/", ".")
                        .substring(packageName.lastIndexOf("com/")) +
                        "." + fileName.substring(0, fileName.length() - 6);
                Class<?> clazz = loader.loadClass(className);
                hiddenClasses.add(clazz);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class<?> clazz : hiddenClasses) {
            String className = clazz.getName();
            className = className.substring(className.lastIndexOf('.') + 1);

            if (className.toLowerCase(Locale.getDefault()).startsWith(key)) {
                try {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException |
                         NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
