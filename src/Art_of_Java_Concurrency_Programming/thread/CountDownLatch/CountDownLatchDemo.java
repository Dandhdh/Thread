package Art_of_Java_Concurrency_Programming.thread.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// await() �����ø÷������̵߳ȵ����췽�������N����0��ʱ�򣬲��ܼ�������ִ�У�
// countDown()��ʹCountDownLatch��ʼֵN��1��

public class CountDownLatchDemo {

    private static CountDownLatch startSignal = new CountDownLatch(1);
    //������ʾ����Ա��Ҫά������6���˶�Ա
    private static CountDownLatch endSignal = new CountDownLatch(6);
    public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(6);

    for (int i = 0; i < 6; i++) {
        executorService.execute(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " �˶�Ա�ȴ�����Ա���ڣ�����");
                startSignal.await();
                System.out.println(Thread.currentThread().getName() + "����ȫ�����");
                endSignal.countDown();
                System.out.println(Thread.currentThread().getName() + "  �����յ�");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    System.out.println("����Ա����ʩ����������");
    startSignal.countDown();
    endSignal.await();
    System.out.println("�����˶�Ա�����յ㣬����������");
    executorService.shutdown();
    }
}