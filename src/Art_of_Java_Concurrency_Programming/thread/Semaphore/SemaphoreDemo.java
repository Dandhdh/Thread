package Art_of_Java_Concurrency_Programming.thread.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    //��ʾ��ʦֻ��10֧��
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {

        //��ʾ50��ѧ��
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "  ͬѧ׼����ȡ��......");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "  ͬѧ��ȡ����");
                    System.out.println(Thread.currentThread().getName() + "  ��д���ing.....");
                    TimeUnit.SECONDS.sleep(3);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "  ��д���񣬹黹�˱ʣ�����������");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }

}
