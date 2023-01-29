package com.javarush.task.task15.task1518;

/* 
Статики и котики
*/

public class Solution {
    public static Cat cat;

    static {
        Cat cat1 = new Cat();
        cat1.name = "H";
        cat = cat1;
    }

    static {
        System.out.println(cat.name);
    }

    public static void main(String[] args) {

    }

    public static class Cat{
        public String name;


    }
}
