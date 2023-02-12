package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//
            String file1 = br.readLine();
            String file2 = br.readLine();

            try (BufferedReader f1 = new BufferedReader(new FileReader(file1));
                 BufferedReader f2 = new BufferedReader(new FileReader(file2))) {

                String str1 = "";
                String str1next = f1.readLine();
                String str2 = "";
                String str2next = f2.readLine();

                try {
                    while (true) {
                        if (str1.equals(str2)) {
                            if (!str1.isEmpty()) {
                                lines.add(new LineItem(Type.SAME, str1));
                            }
                            str1 = str1next;
                            str2 = str2next;
                            str1next = f1.readLine();
                            str2next = f2.readLine();
                        } else if (str2.equals(str1next)) {
                            lines.add(new LineItem(Type.REMOVED, str1));
                            str1 = str1next;
                            str1next = f1.readLine();
                        } else if (str1.equals(str2next)) {
                            lines.add(new LineItem(Type.ADDED, str2));
                            str2 = str2next;
                            str2next = f2.readLine();
                        }
                    }
                } catch (Exception e) {
                    if (str2 == null) {
                        lines.add(new LineItem(Type.REMOVED, str1));
                    } else {
                        lines.add(new LineItem(Type.ADDED, str2));
                    }
                }

                for (LineItem line : lines) {
                    System.out.println(line);
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        @Override
        public String toString() {
            return type + " " + line;
        }
    }
}
