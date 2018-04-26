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
     * ���������̡߳�������WaitThread �� NotifyThread
     * ǰ�ߣ����flag�Ƿ�Ϊfalse�����ϼ�������������������lock�ȴ�
     * ���ߣ�˯����һ��ʱ����lock����֪ͨ
     */
    static class Wait implements Runnable{
        @Override
        public void run(){
            //������ӵ��lock��Monitor
            synchronized (lock){
                //������������ʱ������wait��ͬʱ�ͷ�lock
                while (flag){
                    try{
                        System.out.println(Thread.currentThread() + " flag is true. Wait@ "+
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                        //ע�⣺��wait()�����Ѻ󣬴Ӵ˴���ʼִ��
                    }catch (InterruptedException e){}
                }
                //��������ʱ����ɹ���
                System.out.println(Thread.currentThread() + " flag is flase. Running@ "+
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run(){
            //������ӵ��lock��monitor
            synchronized (lock){
                //��ȡlock������Ȼ�����֪ͨ��֪ͨʱ����֪ͨ��֪ͨʱ�����ͷ�lock����
                //ֱ����ǰ�߳��ͷ���lock��WaitThread���ܴ�wait��������
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
