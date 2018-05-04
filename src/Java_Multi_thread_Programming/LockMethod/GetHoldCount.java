package Java_Multi_thread_Programming.LockMethod;

import java.util.concurrent.locks.ReentrantLock;

//getHoldCount ��ѯ��ǰ�̱߳���������ĸ���
public class GetHoldCount {

    private ReentrantLock lock = new ReentrantLock();

    public  void serviceMethod1(){
        try{
            lock.lock();
            System.out.println("serviceMethod1 getHoldCount="+lock.getHoldCount());
            serviceMethod2();
        }finally {
            lock.unlock();
        }
    }

    public void serviceMethod2(){
        try{
            lock.lock();
            System.out.println("serviceMethod2 getHoldCount="+lock.getHoldCount());
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        GetHoldCount getHoldCount = new GetHoldCount();
        getHoldCount.serviceMethod1();
    }
}
