package Art_of_Java_Concurrency_Programming.thread.Lock;

import Art_of_Java_Concurrency_Programming.util.SleepUtils;
import org.junit.Test;

import java.util.concurrent.locks.Lock;

public class TwinsLockTest {
    @Test
    public void test() {
        //自定义共享锁
        final Lock lock = new TwinsLock();
        //自定义独享锁
        //final Lock lock = new Mutex();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        // 每隔1秒换行
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
            System.out.println();
        }
    }
}