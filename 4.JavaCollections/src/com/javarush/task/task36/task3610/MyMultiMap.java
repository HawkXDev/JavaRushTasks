package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return map.values().stream().mapToInt(List::size).sum();
    }

    @Override
    public V put(K key, V value) {
        V returnedValue = null;

        if (map.containsKey(key)) {
            List<V> values = map.get(key);
            returnedValue = values.get(values.size() - 1);

            if (values.size() == repeatCount) {
                values.remove(0);
            }

            ArrayList<V> newList = new ArrayList<>(values);
            newList.add(value);
            map.put(key, newList);
        } else {
            ArrayList<V> newList = new ArrayList<>();
            newList.add(value);
            map.put(key, newList);
        }

        return returnedValue;
    }

    @Override
    public V remove(Object key) {
        V returnedValue = null;

        if (map.containsKey(key)) {
            List<V> values = map.get(key);
            returnedValue = values.get(0);

            if (values.size() == 1) {
                map.remove(key);
            } else {
                values.remove(0);
            }
        }

        return returnedValue;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> list = new ArrayList<>();
        for (List<V> values : map.values()) {
            list.addAll(values);
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (List<V> values : map.values()) {
            for (V val : values) {
                if (val.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}
