package Art_of_Java_Concurrency_Programming.thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;

//CountDownLatch����һ�������̵߳ȴ������߳���ɲ���
public class CountDownLatchTest {

    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                c.countDown();
                System.out.println("2");
                c.countDown();
            }
        }).start();
        c.await();
        System.out.println("3");
    }

}