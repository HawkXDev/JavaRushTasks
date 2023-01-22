package com.javarush.task.pro.task13.task1305;

import java.util.ArrayList;
import java.util.Iterator;

/* 
Найти и обезвредить
*/

public class Solution {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();
        words.add("Hello world!");
        words.add("Amigo");
        words.add("Elly");
        words.add("Kerry");
        words.add("Bug");
        words.add("bug");
        words.add("Easy ug");
        words.add("Risha");

        ArrayList<String> copyWordsFirst = new ArrayList<>(words);
        ArrayList<String> copyWordsSecond = new ArrayList<>(words);
        ArrayList<String> copyWordsThird = new ArrayList<>(words);

        removeBugWithFor(copyWordsFirst);
        removeBugWithWhile(copyWordsSecond);
        removeBugWithCopy(copyWordsThird);

        copyWordsFirst.forEach(System.out::println);
        String line = "_________________________";
        System.out.println(line);
        copyWordsSecond.forEach(System.out::println);
        System.out.println(line);
        copyWordsThird.forEach(System.out::println);
        System.out.println(line);
    }

    public static void removeBugWithFor(ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if ("bug".equalsIgnoreCase(str)) {
                list.remove(str);
                i--;
            }
        }
    }

    public static void removeBugWithWhile(ArrayList<String> list) {
        var it = list.iterator();
        while (it.hasNext()) {
            if (it.next().toLowerCase().contains("bug"))
                it.remove();
        }
    }

    public static void removeBugWithCopy(ArrayList<String> list) {
        var copy = new ArrayList<>(list);
        for (String s : copy) {
            if (s.toLowerCase().contains("bug"))
                list.remove(s);
        }
    }
}
