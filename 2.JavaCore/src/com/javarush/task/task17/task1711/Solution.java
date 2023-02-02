package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    private static final SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private static final SimpleDateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public static void main(String[] args) throws ParseException {
        Iterator<String> iterator = Arrays.stream(args).iterator();
        iterator.next();

        switch (args[0]) {

            case "-c":
                synchronized (allPeople) {
                    while (iterator.hasNext()) {
                        String name = iterator.next();
                        String sex = iterator.next();
                        Date bd = df1.parse(iterator.next());

                        Person person;
                        if (sex.equals("м")) person = Person.createMale(name, bd);
                        else person = Person.createFemale(name, bd);

                        allPeople.add(person);
                        System.out.println(allPeople.size() - 1);
                    }
                }
                break;

            case "-u":
                synchronized (allPeople) {
                    while (iterator.hasNext()) {
                        int id = Integer.parseInt(iterator.next());
                        String name = iterator.next();
                        Sex sex = mapSex(iterator.next());
                        Date bd = df1.parse(iterator.next());

                        Person person = allPeople.get(id);
                        person.setName(name);
                        person.setSex(sex);
                        person.setBirthDate(bd);
                    }
                }
                break;

            case "-d":
                synchronized (allPeople) {
                    while (iterator.hasNext()) {
                        int id = Integer.parseInt(iterator.next());

                        Person person = allPeople.get(id);
                        person.setName(null);
                        person.setSex(null);
                        person.setBirthDate(null);
                    }
                }
                break;
            case "-i":
                synchronized (allPeople) {
                    while (iterator.hasNext()) {
                        int id = Integer.parseInt(iterator.next());

                        Person person = allPeople.get(id);
                        StringBuilder sb = new StringBuilder(person.getName());
                        sb.append(" ")
                                .append(mapSex(person.getSex()))
                                .append(" ")
                                .append(df2.format(person.getBirthDate()));
                        System.out.println(sb.toString());
                    }
                }
                break;

        }
    }

    private static Sex mapSex(String sex) {
        return sex.equals("м") ? Sex.MALE : Sex.FEMALE;
    }

    private static String mapSex(Sex sex) {
        return sex.equals(Sex.MALE) ? "м" : "ж";
    }
}
