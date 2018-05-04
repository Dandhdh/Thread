package Java_Multi_thread_Programming.Fair_noFair_test;


import java.util.concurrent.locks.ReentrantLock;

/**
 * ��ƽ�����̻߳�ȡ����˳���ǰ��̼߳�����˳��������
 * �ǹ�ƽ����һ�ֻ�ȡ������ռ���ƣ������ȡ��
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
            System.out.println("ThreadName= "+Thread.currentThread().getName()+"�������");
        }finally {
            lock.unlock();
        }
    }

}
