package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static Planet thePlanet;

    static {
        readKeyFromConsoleAndInitPlanet();
    }

    public static void readKeyFromConsoleAndInitPlanet() {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            switch (input) {
                case Planet.EARTH:
                    thePlanet = (Planet) Earth.getInstance();
                    break;
                case Planet.MOON:
                    thePlanet = (Planet) Moon.getInstance();
                    break;
                case Planet.SUN:
                    thePlanet = (Planet) Sun.getInstance();
                    break;
                default:
                    thePlanet = null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
