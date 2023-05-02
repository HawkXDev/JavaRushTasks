package com.javarush.task.task37.task3714.test;

import org.junit.Assert;
import org.junit.Test;

import static com.javarush.task.task37.task3714.Solution.romanToInteger;


public class RomanTest {

    @Test
    public void testRomanToInteger() {
        Assert.assertEquals(1, romanToInteger("I"));
        Assert.assertEquals(2, romanToInteger("II"));
        Assert.assertEquals(3, romanToInteger("III"));
        Assert.assertEquals(4, romanToInteger("IV"));
        Assert.assertEquals(5, romanToInteger("V"));
        Assert.assertEquals(6, romanToInteger("VI"));
        Assert.assertEquals(7, romanToInteger("VII"));
        Assert.assertEquals(8, romanToInteger("VIII"));
        Assert.assertEquals(9, romanToInteger("IX"));
        Assert.assertEquals(10, romanToInteger("X"));
        Assert.assertEquals(40, romanToInteger("XL"));
        Assert.assertEquals(50, romanToInteger("L"));
        Assert.assertEquals(90, romanToInteger("XC"));
        Assert.assertEquals(100, romanToInteger("C"));
        Assert.assertEquals(400, romanToInteger("CD"));
        Assert.assertEquals(500, romanToInteger("D"));
        Assert.assertEquals(900, romanToInteger("CM"));
        Assert.assertEquals(1000, romanToInteger("M"));
        Assert.assertEquals(3999, romanToInteger("MMMCMXCIX"));
    }
}
