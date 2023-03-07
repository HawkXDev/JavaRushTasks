package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Конвертация из одного класса в другой используя JSON Ӏ 3304
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        First first = new First();
        first.i = 100;
        first.name = "First";
        Second second = new Second();
        second.i = 200;
        second.name = "Second";
        Second s = (Second) convertOneToAnother(first, Second.class);
        First f = (First) convertOneToAnother(second, First.class);
        System.out.println(s);
        System.out.println(f);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        ObjectMapper om = new ObjectMapper();
        StringWriter writer = new StringWriter();
        om.writeValue(writer, one);
        String s = writer.toString();
        String key1 = String.format("\"%s\"", one.getClass().getSimpleName().toLowerCase());
        String key2 = String.format("\"%s\"", resultClassObject.getSimpleName().toLowerCase());
        s = s.replaceFirst(key1, key2);
        return om.readValue(s, resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = First.class, name = "first"))
    public static class First {
        public int i;
        public String name;

        @Override
        public String toString() {
            return "First{" +
                    "i=" + i +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = Second.class, name = "second"))
    public static class Second {
        public int i;
        public String name;

        @Override
        public String toString() {
            return "Second{" +
                    "i=" + i +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
