package com.javarush.task.task39.task3913;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        Path logPath = Paths.get(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath())
                .resolve(Solution.class.getPackage().getName().replace('.', '/')).resolve("logs");
        LogParser logParser = new LogParser(logPath);
        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
    }
}
