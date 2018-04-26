package Art_of_Java_Concurrency_Programming.thread;

import Art_of_Java_Concurrency_Programming.util.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args)throws  InterruptedException {
        Thread waitThread = new Thread(new Wait(),"WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);

        Thread notifyThread = new Thread(new Notify(),"NotifyThread");
        notifyThread.start();

    }

    /**
     * 创建两个线程————WaitThread 和 NotifyThread
     * 前者，检查flag是否为false，复合继续后续操作，否则在lock等待
     * 后者，睡眠了一段时间后对lock进行通知
     */
    static class Wait implements Runnable{
        @Override
        public void run(){
            //加锁，拥有lock的Monitor
            synchronized (lock){
                //当条件不满足时，继续wait，同时释放lock
                while (flag){
                    try{
                        System.out.println(Thread.currentThread() + " flag is true. Wait@ "+
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                        //注意：当wait()被唤醒后，从此处开始执行
                    }catch (InterruptedException e){}
                }
                //条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is flase. Running@ "+
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run(){
            //加锁，拥有lock的monitor
            synchronized (lock){
                //获取lock的锁，然后进行通知，通知时进行通知，通知时不会释放lock的锁
                //直到当前线程释放了lock后，WaitThread才能从wait方法返回
                System.out.println(Thread.currentThread() + " hold lock. notify@ "+
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);

            }

            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock again. sleep@ "+
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }

}
