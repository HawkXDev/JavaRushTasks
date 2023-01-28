package com.javarush.task.task14.task1420;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
НОД
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int ost1 = -1;
        int ost2 = -1;
        int nod = Math.min(a,b)+1;
        while (ost1 != 0 || ost2 != 0) {
            nod--;
            ost1 = a % nod;
            ost2 = b % nod;
        }
        System.out.println(nod);
    }
}

