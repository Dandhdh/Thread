package Art_of_Java_Concurrency_Programming.thread.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 * ��ѭ��ʹ�ã�Cyclic�������ϣ�Barrier������Ҫ���������ǣ���һ
 * ���̵߳���һ�����ϣ�Ҳ���Խ�ͬ���㣩ʱ��������
 * ֱ�����һ���̵߳�������ʱ�����ϲŻ�
 * ���ţ����б��������ص��̲߳Ż�������С�
 */
public class CyclicBarrierTest {

    /**
     * ���췽�����������ʾ�������ص��߳���
     * ����ÿ���̵߳���await��������CyclicBarrier���Ѿ����������ϣ�
     * Ȼ��ǰ�̱߳�������
     */
    /**
     * ����������Ϊ3��
     * �����̺߳����̻߳���Զ�ȴ���
     * ��Ϊû�е������߳�ִ��await��������û�е������̵߳������ϣ�
     * ����֮ǰ�������ϵ�����
     * �̶߳��������ִ�С�
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
