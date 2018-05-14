package Art_of_Java_Concurrency_Programming.thread.Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Exchanger��һ�������̼߳�Э���Ĺ����࣬���������̼߳��ܹ�������
 *
 * ���ṩ��һ��������ͬ���㣬�����ͬ���������߳��ܹ��������ݡ�
 * ���彻��������ͨ��exchange������ʵ�ֵģ�
 *
 * ���һ���߳���ִ��exchange��������ô����ͬ���ȴ���һ���߳�Ҳִ��exchange������
 * ���ʱ�������߳̾Ͷ��ﵽ��ͬ���㣬�����߳̾Ϳ��Խ������ݡ�
 *
 */
public class ExchangerDemo {
    private static Exchanger<String> exchanger = new Exchanger();

    public static void main(String[] args) {

        //����������Ů��
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(() -> {
            try {
                //������Ů��˵�Ļ�
                String girl = exchanger.exchange("����ʵ������ܾ���......");
                System.out.println("Ů����˵��" + girl);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            try {
                System.out.println("Ů�������Ĵӽ������߳���......");
                TimeUnit.SECONDS.sleep(3);
                //������Ů��˵�Ļ�
                String boy = exchanger.exchange("��Ҳ��ϲ����......");
                System.out.println("�к���˵��" + boy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}