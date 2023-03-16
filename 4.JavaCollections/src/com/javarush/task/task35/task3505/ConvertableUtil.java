package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.Map;

public class ConvertableUtil {

    public static <K, E extends Convertable<K>> Map<K, E> convert(Iterable<E> list) {
        Map<K, E> result = new HashMap<>();
        for (E element : list) {
            result.put(element.getKey(), element);
        }
        return result;
    }

}
