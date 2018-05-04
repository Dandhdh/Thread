package Java_Multi_thread_Programming.LockMethod;


import Java_Multi_thread_Programming.ConditionTestManyToMany.Run;

import java.util.concurrent.locks.ReentrantLock;

/**
 * getQueueLength �������ڵȴ����������̹߳�����
 *
 * ���磺������̣߳�1�߳�����ִ��await��������ô����getQueueLength()�����󷵻�ֵΪ4
 * ˵�����ĸ��߳����ڵȴ�lock���ͷ�
 */
public class GetQueueLength {

    public ReentrantLock lock = new ReentrantLock();
    public  void serviceMethod(){
        try{
            lock.lock();
            System.out.println("ThreadName = "+Thread.currentThread().getName() +"���뷽������");
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
        System.out.println("���߳�����" + getQueueLength.lock.getQueueLength()+" �ڵȴ��̣߳���");
    }

}
