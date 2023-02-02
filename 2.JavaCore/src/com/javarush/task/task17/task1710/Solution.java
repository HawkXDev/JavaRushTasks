package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        String firstParam = args[0];
        if (firstParam.equals("-c")) {
            String name = args[1];
            String sex = args[2];
            String bd = args[3];
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy",
                    Locale.ENGLISH);
            if (sex.equals("м")) {
                allPeople.add(Person.createMale(name, df.parse(bd)));
            } else if (sex.equals("ж")) {
                allPeople.add(Person.createFemale(name, df.parse(bd)));
            }
            System.out.println(allPeople.size() - 1);

        } else if (firstParam.equals("-r")) {
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            String sex = person.getSex() == Sex.MALE ? "м" : "ж";
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",
                    Locale.ENGLISH);
            String dateStr = df.format(person.getBirthDate());
            System.out.println(person.getName() + " " + sex + " " + dateStr);

        } else if (firstParam.equals("-u")) {
            int id = Integer.parseInt(args[1]);
            String name = args[2];
            String sex = args[3];
            String bd = args[4];
            Person person = allPeople.get(id);
            person.setSex(sex.equals("м") ? Sex.MALE : Sex.FEMALE);
            person.setName(name);
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy",
                    Locale.ENGLISH);
            person.setBirthDate(df.parse(bd));

        } else if (firstParam.equals("-d")) {
            int id = Integer.parseInt(args[1]);
            Person person = allPeople.get(id);
            person.setSex(null);
            person.setName(null);
            person.setBirthDate(null);
        }
    }
}
