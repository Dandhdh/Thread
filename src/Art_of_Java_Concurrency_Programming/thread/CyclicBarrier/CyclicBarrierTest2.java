package Art_of_Java_Concurrency_Programming.thread.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest2 {

    /**
     * 构造函数CyclicBarrier（int parties，Runnable barrier-Action）
     * 用于在线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景，
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
