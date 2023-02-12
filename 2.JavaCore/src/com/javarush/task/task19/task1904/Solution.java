package com.javarush.task.task19.task1904;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String line = fileScanner.nextLine();
            String[] arr = line.split(" ");
            String firstName = arr[1];
            String middleName = arr[2];
            String lastName = arr[0];
            LocalDate birthDate = LocalDate.of(Integer.parseInt(arr[5]), Integer.parseInt(arr[4]),
                    Integer.parseInt(arr[3]));
            return new Person(firstName, middleName, lastName,
                    Date.from(birthDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
