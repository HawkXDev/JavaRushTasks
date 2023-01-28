package com.javarush.task.task14.task1411;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

/* 
User, Loser, Coder and Proger
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = null;
        String key = null;
        String[] res = {"user", "loser", "coder", "proger"};
        Arrays.sort(res);

        while (Arrays.binarySearch(res, (key = reader.readLine())) != -1) {
            if (Objects.equals(key, "user")) {
                person = new Person.User();
            } else if (Objects.equals(key, "loser")) {
                person = new Person.Loser();
            } else if (Objects.equals(key, "coder")) {
                person = new Person.Coder();
            } else if (Objects.equals(key, "proger")) {
                person = new Person.Proger();
            } else {
                return;
            }

            doWork(person);

        }
    }

    public static void doWork(Person person) {
        if (person instanceof Person.User) {
            ((Person.User) person).live();
        } else if (person instanceof Person.Loser) {
            ((Person.Loser) person).doNothing();
        } else if (person instanceof Person.Coder) {
            ((Person.Coder) person).writeCode();
        } else if (person instanceof Person.Proger) {
            ((Person.Proger) person).enjoy();
        }
    }
}
