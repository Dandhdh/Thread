package Art_of_Java_Concurrency_Programming.thread;

import java.util.concurrent.TimeUnit;

public class Shutdown {

    public static void main(String[] args) throws InterruptedException{
        Runner one = new Runner();
        Thread countThread = new Thread(one,"CountThread");
        countThread.start();

        //˯��1�룬main�̶߳�countThread�����жϣ�ʹ��countThread�ܹ���֪�ж϶�����
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two,"CountThread");
        countThread.start();

        //˯��1�룬main�̶߳�Runner two����ȡ����ʹ��countThread�ܹ���֪onΪfalse������
        TimeUnit.SECONDS.sleep(1);
        two.cancel();

    }

    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;

        /**
         * ͨ����ʶλ�����жϲ����ķ�ʽ�ܹ��߳�����ֹʱ�л���ȥ������Դ
         */
        @Override
        public void run(){
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = "+i);
        }
        public void cancel(){
            on = false;
        }
    }

}
