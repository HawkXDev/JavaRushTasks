package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

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
        FileBucket bucket = table[index];
        for (Entry e = bucket.getEntry(); e != null; e = e.next) {
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
        FileBucket bucket = table[index];
        if (bucket != null) {
            for (Entry e = bucket.getEntry(); e != null; e = e.next) {
                if (key.equals(e.key)) {
                    e.value = value;
                    return;
                }
            }
        }
        addEntry(hash, key, value, index);
    }

    void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    void transfer(FileBucket[] newTable) {
        int newCapacity = newTable.length;
        for (FileBucket bucket : table) {
            Entry entry = bucket.getEntry();
            int indexInNewTable = indexFor(entry.hash, newCapacity);
            FileBucket newBucket = new FileBucket();
            newBucket.putEntry(entry);
            newTable[indexInNewTable] = newBucket;
            bucket.remove();
        }
    }

    @Override
    public boolean containsValue(String value) {
        for (FileBucket bucket : table) {
            if (bucket != null) {
                Entry entry = bucket.getEntry();
                while (entry != null) {
                    if (entry.getValue().equals(value)) {
                        return true;
                    }
                    entry = entry.next;
                }
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
        for (FileBucket bucket : table) {
            Entry entry = bucket.getEntry();
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
        FileBucket bucket = table[bucketIndex];
        if (bucket != null) {
            if (bucket.getFileSize() >= bucketSizeLimit) {
                resize(2 * table.length);
                hash = hash(key);
                bucketIndex = indexFor(hash, table.length);
            }
        }

        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex) {
        FileBucket bucket = table[bucketIndex];
        Entry existingEntry = null;
        if (bucket != null) {
            existingEntry = bucket.getEntry();
        } else {
            bucket = new FileBucket();
            table[bucketIndex] = bucket;
        }
        Entry newEntry = new Entry(hash, key, value, existingEntry);
        bucket.putEntry(newEntry);
        size++;
    }
}
