package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/* 
Решаем пример
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream defaultOut = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        System.setOut(printStream);
        testString.printSomething();
        System.setOut(defaultOut);
        String primer = byteArrayOutputStream.toString();
        primer = primer.substring(0, primer.length() - 1);

        String[] arr = byteArrayOutputStream.toString().split(" ");
        int n1 = Integer.parseInt(arr[0]);
        String znak = arr[1];
        int n2 = Integer.parseInt(arr[2]);
        switch (znak) {
            case "+":
                System.out.println(primer + (n1 + n2));
                break;
            case "-":
                System.out.println(primer + (n1 - n2));
                break;
            case "*":
                System.out.println(primer + (n1 * n2));
                break;
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

