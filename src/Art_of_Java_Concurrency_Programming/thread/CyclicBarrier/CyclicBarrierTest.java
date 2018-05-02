package Art_of_Java_Concurrency_Programming.thread.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 * 可循环使用（Cyclic）的屏障（Barrier）。它要做的事情是，让一
 * 组线程到达一个屏障（也可以叫同步点）时被阻塞，
 * 直到最后一个线程到达屏障时，屏障才会
 * 开门，所有被屏障拦截的线程才会继续运行。
 */
public class CyclicBarrierTest {

    /**
     * 构造方法，其参数表示屏障拦截的线程数
     * 量，每个线程调用await方法告诉CyclicBarrier我已经到达了屏障，
     * 然后当前线程被阻塞。
     */
    /**
     * 如果其参数改为3，
     * 则主线程和子线程会永远等待，
     * 因为没有第三个线程执行await方法，即没有第三个线程到达屏障，
     * 所以之前到达屏障的两个
     * 线程都不会继续执行。
     */
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                }catch (Exception e){
                }
                System.out.println(1);
            }
        }).start();
        try{
            cyclicBarrier.await();
        }catch (Exception e){
        }
        System.out.println(2);
    }

}
