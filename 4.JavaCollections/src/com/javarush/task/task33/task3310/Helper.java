package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {
    private static final int LENGTH = 20;

    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        BigInteger bigInt = new BigInteger(LENGTH * 5, random);
        return bigInt.toString(32);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
