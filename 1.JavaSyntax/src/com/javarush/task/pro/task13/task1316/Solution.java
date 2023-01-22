package com.javarush.task.pro.task13.task1316;

public class Solution {

    public static void main(String[] args) {
        JavarushQuest[] arr = JavarushQuest.values();
        for (JavarushQuest javarushQuest : arr)
            System.out.println(javarushQuest.ordinal());
    }
}
