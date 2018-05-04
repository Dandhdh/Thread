package Java_Multi_thread_Programming.MustUseMoreCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//ʹ�ö��Conditionʵ��֪ͨ�����߳�
//�뵥�����Ѳ����߳���ô������ʱ����Ҫ���condition����Ҳ����condition���Ի��Ѳ���ָ���߳�

/**
 * Object��wait() �൱�� Condition��await()
 * Object��notify() �൱�� Condition��signal()
 * Object��notifyAll() �൱�� Condition��signalAll()
 */
public class MyService {

    private Lock lock = new ReentrantLock();
    public Condition conditionA = lock.newCondition();
    public Condition conditionB = lock.newCondition();

    public void awaitA(){
        try{
            lock.lock();
            System.out.println("begin awaitA ʱ��Ϊ "+System.currentTimeMillis()
                    + "ThreadName = " + Thread.currentThread().getName());
            conditionA.await();
            System.out.println(" end  awaitA ʱ��Ϊ "+System.currentTimeMillis()
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
            System.out.println("begin awaitB ʱ��Ϊ "+System.currentTimeMillis()
                    + "ThreadName = " + Thread.currentThread().getName());
            conditionB.await();
            System.out.println(" end  awaitB ʱ��Ϊ "+System.currentTimeMillis()
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
            System.out.println(" signalAll_A ʱ��Ϊ "+System.currentTimeMillis()
                    + "ThreadName = " + Thread.currentThread().getName());
            conditionA.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void signalAll_B(){
        try{
            lock.lock();
            System.out.println(" signalAll_B ʱ��Ϊ "+System.currentTimeMillis()
                    + "ThreadName = " + Thread.currentThread().getName());
            conditionB.signalAll();
        }finally {
            lock.unlock();
        }
    }

}
