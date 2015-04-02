package local.Test.threads.task3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWDictionary {
    private final Map<String, Integer> m = new TreeMap<String, Integer>();
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public Integer get(String key) {
        r.lock();
        try { return m.get(key); }
        finally { r.unlock(); }
    }
    public String[] allKeys() {
        r.lock();
        Set<String> stringSet = m.keySet();
        String[] arr = new String[stringSet.size()];
        Iterator<String> itr = stringSet.iterator();
        for (int i = 0; i < stringSet.size(); i++){
        	arr[i] = itr.next();
        }
        //(String[])m.keySet().toArray();
        try { return arr; }
        finally { r.unlock(); }
    }
    public Integer put(String key, Integer value) {
        w.lock();
        try { return m.put(key, value); }
        finally { w.unlock(); }
    }
    public void clear() {
        w.lock();
        try { m.clear(); }
        finally { w.unlock(); }
    }
}
