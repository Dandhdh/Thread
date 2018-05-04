package Java_Multi_thread_Programming.LockMethod;


import Java_Multi_thread_Programming.ConditionTestManyToMany.Run;

import java.util.concurrent.locks.ReentrantLock;

/**
 * getQueueLength 返回正在等待此锁定的线程估计数
 *
 * 比如：有五个线程，1线程首先执行await方法，那么调用getQueueLength()方法后返回值为4
 * 说明有四个线程正在等待lock的释放
 */
public class GetQueueLength {

    public ReentrantLock lock = new ReentrantLock();
    public  void serviceMethod(){
        try{
            lock.lock();
            System.out.println("ThreadName = "+Thread.currentThread().getName() +"进入方法！！");
            Thread.sleep(Integer.MAX_VALUE);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final GetQueueLength getQueueLength = new GetQueueLength();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                getQueueLength.serviceMethod();
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
        System.out.println("有线程数：" + getQueueLength.lock.getQueueLength()+" 在等待线程！！");
    }

}
