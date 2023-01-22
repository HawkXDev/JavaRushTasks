package com.javarush.task.pro.task13.task1309;

import java.util.*;

/* 
Успеваемость студентов
*/

public class Solution {
    public static HashMap<String, Double> grades = new HashMap<>();

    public static void main(String[] args) {
        addStudents();
        System.out.println(grades);
    }

    public static void addStudents() {
        ArrayList<String> students = new ArrayList<>() {{
            add("Вася");
            add("Петя");
            add("Юля");
            add("Гога");
            add("Миша");
        }};
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            String student = students.get(r.nextInt(students.size()));
            students.remove(student);
            double randomGrade = r.nextDouble() * 5 + 5;
            double grade = (double) Math.round(randomGrade * 10) / 10;
            grades.put(student, grade);
        }
    }
}
