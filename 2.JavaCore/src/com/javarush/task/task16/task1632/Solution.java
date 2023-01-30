package com.javarush.task.task16.task1632;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Клубок
*/

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new Thread1());
        threads.add(new Thread2());
        threads.add(new Thread3());
        threads.add(new Thread4());
        threads.add(new Thread5());
    }

    public static void main(String[] args) {


//        for (Thread thread : threads) {
//            thread.start();
//        }
    }

    public static class Thread1 extends Thread {
        @Override
        public void run() {
            while (true) {

            }
        }
    }

    public static class Thread2 extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class Thread3 extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("Ура");
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class Thread4 extends Thread implements Message {
        @Override
        public void run() {
            while (isAlive()) {

            }
        }

        @Override
        public void showWarning() {
            interrupt();
        }
    }

    public static class Thread5 extends Thread {
        @Override
        public void run() {
            try (Scanner scanner = new Scanner(System.in)) {
                String input;
                double sum = 0;
                while (!(input = scanner.nextLine()).equals("N")) {
                    try {
                        double d = Double.parseDouble(input);
                        sum += d;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(sum);
            }
        }
    }
}