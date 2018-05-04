package Java_Multi_thread_Programming.Fair_noFair_test;


import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁：线程获取锁的顺序是按线程加锁的顺序来分配
 * 非公平锁：一种获取锁的抢占机制，随机获取锁
 */
public class Service {

    private ReentrantLock lock;

    public Service(boolean isFair){
        super();
        lock = new ReentrantLock(isFair);
    }

    public  void serviceMethod(){
        try{
            lock.lock();
            System.out.println("ThreadName= "+Thread.currentThread().getName()+"获得锁定");
        }finally {
            lock.unlock();
        }
    }

}
