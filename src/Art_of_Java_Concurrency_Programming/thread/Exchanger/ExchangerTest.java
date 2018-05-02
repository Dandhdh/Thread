package Art_of_Java_Concurrency_Programming.thread.Exchanger;

/**
 * Exchanger
 * �̼߳佻������
 *
 * ���ṩһ��ͬ���㣬�����ͬ���㣬�����߳̿��Խ����˴˵����ݡ��������߳�ͨ��
 * exchange�����������ݣ������һ���߳���ִ��exchange()����������һֱ�ȴ��ڶ����߳�Ҳ
 * ִ��exchange�������������̶߳�����ͬ����ʱ���������߳̾Ϳ��Խ������ݣ������߳�����
 * ���������ݴ��ݸ��Է���
 */

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExchangerӦ�ó���
 * �Ŵ��㷨���Ŵ��㷨����Ҫѡ����������Ϊ���������ʱ��ύ�����˵����ݣ���ʹ�ý������ó�2����������
 *
 * У�Թ���������������
 * Ҫ��ֽ��������ˮͨ���˹��ķ�ʽ¼��ɵ���������ˮ��Ϊ�˱�����󣬲���AB�����˽���
 * ¼�룬¼�뵽Excel֮��ϵͳ��Ҫ����������Excel����������Excel���ݽ���У�ԣ������Ƿ�
 * ¼��һ�£�
 */
public class ExchangerTest {

    private static final Exchanger<String> exgr = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    String A = "������ˮA";
                    // A¼��������ˮ����
                    exgr.exchange(A);
                }catch (InterruptedException e){}
            }
        });

        /**
         * ��������߳���һ��û��ִ��exchange()���������һֱ�ȴ���������������������
         * ��������һֱ�ȴ�������ʹ��exchange��V x��longtimeout��TimeUnit unit���������ȴ�ʱ��
         */
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "������ˮB";
                    // B¼��������ˮ����
                    String A = exgr.exchange("B");
                    System.out.println("A��B�����Ƿ�һ�£�" + A.equals(B) + "��A¼����ǣ�"
                            + A + "��B¼���ǣ�" + B);
                }catch (InterruptedException e){}
            }
        });
        threadPool.shutdown();
    }

}
