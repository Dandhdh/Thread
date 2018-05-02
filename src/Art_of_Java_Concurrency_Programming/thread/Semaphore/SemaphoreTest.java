package Art_of_Java_Concurrency_Programming.thread.Semaphore;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore
 * ���Ʋ����߳���
 * ��������ͬʱ�����ض���Դ���߳���������ͨ��Э�������̣߳��Ա�֤�����ʹ�ù�����Դ��
 *
 * Ӧ�ó���
 * �ر��ǹ�����Դ���޵�Ӧ�ó������������ݿ����ӡ���
 * ����һ������Ҫ��ȡ������ļ������ݣ���Ϊ����IO�ܼ����������ǿ���������ʮ���߳�
 * �����ض�ȡ��������������ڴ�󣬻���Ҫ�洢�����ݿ��У������ݿ��������ֻ��10������
 * ʱ���Ǳ������ֻ��10���߳�ͬʱ��ȡ���ݿ����ӱ������ݣ�����ᱨ���޷���ȡ���ݿ���
 * �ӡ����ʱ�򣬾Ϳ���ʹ��Semaphore������������
 */

public class SemaphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors
            .newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {

        for(int i=0; i<THREAD_COUNT; i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        s.acquire();
                        System.out.println("save data");
                        s.release();
                    }catch (InterruptedException e){}
                }
            });
        }
        threadPool.shutdown();
    }
}
