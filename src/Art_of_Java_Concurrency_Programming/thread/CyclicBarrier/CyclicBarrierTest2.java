package Art_of_Java_Concurrency_Programming.thread.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {

    /**
     * ���캯��CyclicBarrier��int parties��Runnable barrier-Action��
     * �������̵߳�������ʱ������ִ��barrierAction�����㴦������ӵ�ҵ�񳡾���
     */
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2,new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    cyclicBarrier.await();
                }catch (Exception e){}
                System.out.println(1);
            }
        }).start();
        try {
            cyclicBarrier.await();
        }catch (Exception e){}
        System.out.println(2);
    }

    static class A implements Runnable{
        @Override
        public void run(){
            System.out.println(3);
        }
    }

}
