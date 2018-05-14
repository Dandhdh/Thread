package Art_of_Java_Concurrency_Programming.thread.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
    //ָ��������6���˶�Ա�������
    private static CyclicBarrier barrier = new CyclicBarrier(6, () -> {
        System.out.println("�����˶�Ա�볡������Աһ�����£���������");
    });
    public static void main(String[] args) {
        System.out.println("�˶�Ա׼��������ȫ������............");

        ExecutorService service = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            service.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " �˶�Ա������");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + "  �˶�Ա����");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
