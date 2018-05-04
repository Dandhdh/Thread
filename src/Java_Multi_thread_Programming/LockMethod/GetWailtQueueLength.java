package Java_Multi_thread_Programming.LockMethod;

import Java_Multi_thread_Programming.ConditionTestManyToMany.Run;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * getWailtQueueLength：返回等待与此锁定相关的给定条件Condition的线程估计数
 *
 * 如：有五个线程，每一个线程都执行了同一个condition对象的await()方法，
 * 则调用该getWailtQueueLength(Condition condition)时返回5
 */
public class GetWailtQueueLength {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void waitMethod(){
        try{
            lock.lock();
            condition.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void notityMethod(){
        try{
            lock.lock();
            System.out.println(" 有 "+lock.getWaitQueueLength(condition)
                    + "个线程正在等待 condition");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        final GetWailtQueueLength getWailtQueueLength = new GetWailtQueueLength();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getWailtQueueLength.waitMethod();
            }
        };
        Thread[] threads = new Thread[10];
        for(int i=0;i<10;i++){
            threads[i] = new Thread(runnable);
        }
        for(int i=0;i<10;i++){
            threads[i].start();
        }
        Thread.sleep(2000);
        getWailtQueueLength.notityMethod();
    }

}
