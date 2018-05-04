package Java_Multi_thread_Programming.MustUseMoreCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//使用多个Condition实现通知部分线程
//想单独唤醒部分线程怎么处理？这时候需要多个condition对象，也就是condition可以唤醒部分指定线程

/**
 * Object的wait() 相当于 Condition的await()
 * Object的notify() 相当于 Condition的signal()
 * Object的notifyAll() 相当于 Condition的signalAll()
 */
public class MyService {

    private Lock lock = new ReentrantLock();
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();

    public void awaitA(){
        try{
            lock.lock();
            System.out.println("begin awaitA 时间为 "+System.currentTimeMillis()
                    + "ThreadName = " + Thread.currentThread().getName());
            conditionA.await();
            System.out.println(" end  awaitA 时间为 "+System.currentTimeMillis()
                    + "ThreadName = " + Thread.currentThread().getName());
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void awaitB(){
        try{
            lock.lock();
            System.out.println("begin awaitB 时间为 "+System.currentTimeMillis()
                    + "ThreadName = " + Thread.currentThread().getName());
            conditionB.await();
            System.out.println(" end  awaitB 时间为 "+System.currentTimeMillis()
                    + "ThreadName = " + Thread.currentThread().getName());
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signalAll_A(){
        try{
            lock.lock();
            System.out.println(" signalAll_A 时间为 "+System.currentTimeMillis()
                    + "ThreadName = " + Thread.currentThread().getName());
            conditionA.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void signalAll_B(){
        try{
            lock.lock();
            System.out.println(" signalAll_B 时间为 "+System.currentTimeMillis()
                    + "ThreadName = " + Thread.currentThread().getName());
            conditionB.signalAll();
        }finally {
            lock.unlock();
        }
    }

}
