package cn.luxinhuo.concurrent_coding.stage1.LockLearn;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class ReadWriteLockLearn {

    private final Map<String,Date> map= new ConcurrentHashMap<>();

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public Date get(String key){
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Date delete(String key){
        writeLock.lock();
        try {
            return map.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public Date add(String key,Date date){
        writeLock.lock();
        try {
            return map.put(key, date);
        } finally {
            writeLock.unlock();
        }
    }

    private final StampedLock stampedLock = new StampedLock();

    public void read(String key){
        long stamp = stampedLock.readLock();
        try {
            map.get(key);
        } finally {
            stampedLock.unlock(stamp);
        }
    }
    public void write(String key,Date date){
        long stamp = stampedLock.writeLock();
        try {
            map.put(key, date);
        } finally {
            stampedLock.unlock(stamp);
        }
    }
}
