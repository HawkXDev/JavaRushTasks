package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();

    public V getByKey(K key, Class<V> clazz) throws Exception {
        return cache.computeIfAbsent(key, (K k) -> {
            try {
                Class<?>[] params = {k.getClass()};
                return clazz.getConstructor(params).newInstance(k);
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public boolean put(V obj) {
        try {
            Method getKey = obj.getClass().getDeclaredMethod("getKey");
            getKey.setAccessible(true);
            K key = (K) getKey.invoke(obj);
            cache.put(key, obj);
            return true;
        } catch (ClassCastException | NoSuchMethodException | InvocationTargetException
                 | IllegalAccessException ignored) {
            return false;
        }
    }

    public int size() {
        return cache.size();
    }
}
