package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    Entry[] table = new Entry[DEFAULT_INITIAL_CAPACITY];
    int size;
    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    float loadFactor = DEFAULT_LOAD_FACTOR;

    final int hash(Long k) {
        return k.hashCode();
    }

    static int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    final Entry getEntry(Long key) {
        if (size == 0) {
            return null;
        }

        int hash = hash(key);
        int index = indexFor(hash, table.length);
        for (Entry e = table[index]; e != null; e = e.next) {
            if (key.equals(e.key)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        for (Entry e = table[index]; e != null; e = e.next) {
            if (key.equals(e.key)) {
                e.value = value;
                return;
            }
        }
        addEntry(hash, key, value, index);
    }

    void resize(int newCapacity) {
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    void transfer(Entry[] newTable) {
        int newCapacity = newTable.length;
        for (Entry entry : table) {
            while (entry != null) {
                Entry next = entry.next;
                int indexInNewTable = indexFor(entry.hash, newCapacity);
                entry.next = newTable[indexInNewTable];
                newTable[indexInNewTable] = entry;
                entry = next;
            }
        }
    }

    @Override
    public boolean containsValue(String value) {
        for (Entry entry : table) {
            while (entry != null) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public Long getKey(String value) {
        for (Entry entry : table) {
            while (entry != null) {
                if (entry.getValue().equals(value)) {
                    return entry.getKey();
                }
                entry = entry.next;
            }
        }
        return null;
    }

    void addEntry(int hash, Long key, String value, int bucketIndex) {
        if (size >= threshold) {
            resize(2 * table.length);
            hash = hash(key);
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        Entry existingEntry = table[bucketIndex];
        table[bucketIndex] = new Entry(hash, key, value, existingEntry);
        size++;
    }
}
