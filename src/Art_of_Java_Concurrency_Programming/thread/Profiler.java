package Art_of_Java_Concurrency_Programming.thread;

import java.util.concurrent.TimeUnit;

/**
 * ����һ������profiler�࣬����begin()��end()��������
 * end()�������ش�begin()�������ÿ�ʼ��end()���������õ�ʱ���
 */
public class Profiler {

    //��һ��get()��������ʱ����г�ʼ�������set����û�е��ã���ÿ���̻߳����һ��
    private  static  final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public  static  final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException{
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end()+" mils");
    }

}
