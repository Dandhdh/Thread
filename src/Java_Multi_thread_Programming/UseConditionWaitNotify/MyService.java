package Java_Multi_thread_Programming.UseConditionWaitNotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//使用Condition实现通知等待

/**
 * Object的wait() 相当于 Condition的await()
 * Object的notify() 相当于 Condition的signal()
 * Object的notifyAll() 相当于 Condition的signalAll()
 */
public class MyService {

    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void await(){
        try{
            lock.lock();
            System.out.println("await 时间为 "+System.currentTimeMillis());
            condition.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signal(){
        try {
            lock.lock();
            System.out.println("signal 时间为 "+System.currentTimeMillis());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

}
