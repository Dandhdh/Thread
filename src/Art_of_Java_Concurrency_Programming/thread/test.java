package Art_of_Java_Concurrency_Programming.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class test {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread thread = new Thread();

        ConcurrentHashMap hashMap = new ConcurrentHashMap();

    }
}
