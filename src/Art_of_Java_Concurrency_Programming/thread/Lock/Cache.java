package Art_of_Java_Concurrency_Programming.thread.Lock;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Cache���һ�����̰߳�ȫ��HashMap��Ϊ�����ʵ��
 * ͬʱʹ�ö�д���Ķ�����д������֤Cache���̰߳�ȫ�ġ�
 */
public class Cache {

    static Map<String, Object> map = new HashMap<String, Object>();

    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    // ��ȡһ��key��Ӧ��value
    public static final Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    // ����key��Ӧ��value�������ؾɵ�value
    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }
    // ������е�����
    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}