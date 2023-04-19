package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class SpeedTest {
    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        IntStream.range(0, 10000).forEach(i -> origStrings.add(Helper.generateRandomString()));

        Set<Long> ids1 = new HashSet<>();
        Set<Long> ids2 = new HashSet<>();
        long time1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long time2 = getTimeToGetIds(shortener2, origStrings, ids2);

        Assert.assertTrue(time1 > time2);

        Set<String> setStr1 = new HashSet<>();
        Set<String> setStr2 = new HashSet<>();
        long time3 = getTimeToGetStrings(shortener1, ids1, setStr1);
        long time4 = getTimeToGetStrings(shortener2, ids1, setStr2);

        Assert.assertEquals(time3, time4, 30);
    }

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        long startTime = System.currentTimeMillis();
        for (String s : strings) {
            ids.add(shortener.getId(s));
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        long startTime = System.currentTimeMillis();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
