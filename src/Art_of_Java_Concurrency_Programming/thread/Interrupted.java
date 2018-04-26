package Art_of_Java_Concurrency_Programming.thread;

import Art_of_Java_Concurrency_Programming.util.SleepUtils;

import java.util.concurrent.TimeUnit;

public class Interrupted {

    public static void main(String[] args) throws InterruptedException {

        //��ͣ�ĳ���˯��
        Thread sleepThread = new Thread(new SleepRunner(),"SleepThread");
        sleepThread.setDaemon(true);
        //��ͣ������߳�
        Thread busyThread = new Thread(new BusyRunner(),"BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();

        //����5�룬���̳߳������
        TimeUnit.SECONDS.sleep(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread interrupt is "+sleepThread.isInterrupted());
        System.out.println("busyThread interrupt is "+busyThread.isInterrupted());



    }

    static class SleepRunner implements Runnable{
        @Override
        public void run(){
            while (true){
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable{
        @Override
        public void run(){
            while (true){
            }
        }
    }

}
