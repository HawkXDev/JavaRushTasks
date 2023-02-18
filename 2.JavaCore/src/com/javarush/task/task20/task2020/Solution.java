package com.javarush.task.task20.task2020;

import java.io.*;
import java.util.logging.Logger;

/* 
Сериализация человека
*/

public class Solution {

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greeting;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.greeting = "Hello, ";
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", fullName='" + fullName + '\'' +
                    ", greeting='" + greeting + '\'' +
                    ", country='" + country + '\'' +
                    ", sex=" + sex + ",\n" +
                    "outputStream=" + outputStream +
                    '}';
        }
    }

    enum Sex {
        MALE,
        FEMALE
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person("1", "2", "3", Sex.FEMALE);
        System.out.println(person);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeObject(person);
        out.close();
        byte[] data = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInput oi = new ObjectInputStream(byteArrayInputStream);
        Person newPerson = (Person) oi.readObject();
        System.out.println(newPerson);
    }
}
