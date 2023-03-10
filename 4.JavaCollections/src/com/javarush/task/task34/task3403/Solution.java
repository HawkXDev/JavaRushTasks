package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/

public class Solution {
    public void recurse(int n) {
        if (n <= 1) return;
        int i = 2;
        while (n % i != 0) i++;
        System.out.print(i + " ");
        recurse(n / i);
    }

    public static void main(String[] args) {
        new Solution().recurse(132);
    }
}
