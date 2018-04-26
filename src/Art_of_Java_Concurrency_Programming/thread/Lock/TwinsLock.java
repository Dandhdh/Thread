package Art_of_Java_Concurrency_Programming.thread.Lock;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

public class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must larger than zero.");
            }
            setState(count);
        }

        @Override
        public int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current,
                        newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }


    // 其他接口方法略，还没实现
    @Override
    public  void lockInterruptibly(){}
    @Override
    public  boolean tryLock(){return true;};
    @Override
    public  boolean tryLock(long time, TimeUnit unit){return true;}

    @Override
    public Condition newCondition(){
        return new Condition() {
            @Override
            public void await() throws InterruptedException {

            }

            @Override
            public void awaitUninterruptibly() {

            }

            @Override
            public long awaitNanos(long nanosTimeout) throws InterruptedException {
                return 0;
            }

            @Override
            public boolean await(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public boolean awaitUntil(Date deadline) throws InterruptedException {
                return false;
            }

            @Override
            public void signal() {

            }

            @Override
            public void signalAll() {

            }
        };
    }

}