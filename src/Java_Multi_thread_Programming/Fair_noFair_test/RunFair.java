package Java_Multi_thread_Programming.Fair_noFair_test;

import Java_Multi_thread_Programming.ConditionTest.Run;
import Java_Multi_thread_Programming.ConditionTest.ThreadB;

public class RunFair {

    public static void main(String[] args) {

        final Service service = new Service(true);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(" * �߳�"+Thread.currentThread().getName()+"������");
                service.serviceMethod();
            }
        };

        Thread[] threadArray = new Thread[10];
        for(int i=0; i<10; i++){
            threadArray[i] = new Thread(runnable);
        }
        for(int i=0; i<10; i++){
            threadArray[i].start();
        }

    }
}
