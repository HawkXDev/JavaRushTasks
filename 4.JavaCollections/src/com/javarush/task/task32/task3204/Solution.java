package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    private static final char[] symbols = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c',
            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z'};

    public static ByteArrayOutputStream getPassword() {
        char[] pw = new char[8];
        boolean isUnique;
        do {
            isUnique = true;
            for (int i = 0; i < 8; i++) {
                int r = getRandom(symbols.length);
                char c = symbols[r];
                if (r > 9) {
                    r = getRandom(2);
                    if (r == 0)
                        c = String.valueOf(c).toUpperCase().charAt(0);
                }
                pw[i] = c;
            }
            if (!isMinOneNumber(pw)) isUnique = false;
            if (!isMinOneUppLetter(pw)) isUnique = false;
            if (!isMinOneLowLetter(pw)) isUnique = false;
        } while (!isUnique);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (char c : pw) {
            out.write(c);
        }
        return out;
    }

    private static int getRandom(int length) {
        return (int) (Math.random() * length);
    }

    private static boolean isMinOneLowLetter(char[] pw) {
        for (char c : pw) {
            int i = findIndex(c);
            if (i > 9) {
                char c2 = String.valueOf(c).toLowerCase().charAt(0);
                if (c == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isMinOneUppLetter(char[] pw) {
        for (char c : pw) {
            int i = findIndex(c);
            if (i > 9) {
                char c2 = String.valueOf(c).toUpperCase().charAt(0);
                if (c == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isMinOneNumber(char[] pw) {
        for (char c : pw) {
            int i = findIndex(c);
            if (i >= 0 && i < 10) {
                return true;
            }
        }
        return false;
    }

    private static int findIndex(char c) {
        int i = -1;
        do {
            i++;
        } while (i < symbols.length && symbols[i] != c);
        return i;
    }

}
