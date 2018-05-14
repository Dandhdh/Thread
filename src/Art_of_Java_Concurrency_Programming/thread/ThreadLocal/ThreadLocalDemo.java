package Art_of_Java_Concurrency_Programming.thread.ThreadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal ������������������Ķ��̷߳�������ģ�
 * ����ʵ�����Ƿ���ÿ��threadʵ�����õ�threadLocalMap,
 * Ҳ����˵ÿ����ͬ���̶߳�ӵ��ר�����Լ�������������threadLocalMap�����˴˲�Ӱ�졣
 *
 * ���threadLocalֻ������ ������������̰߳�ȫ ��ҵ�񳡾���
 * ����
 * hibernate��ͨ��threadLocal����Session����һ�����͵İ�����
 * ��ͬ�������̣߳��û���ӵ���Լ���session,����session�����ȥ�����̷߳��ʣ�
 * ��Ȼ������̰߳�ȫ���⡣
 *
 * һ�����ӣ�SimpleDateFormat.parse���������̰߳�ȫ�����⣬
 * ���ǿ��Գ���ʹ��threadLocal��װSimpleDateFormat������ʵ���������̹߳����ɡ�
 *
 */
public class ThreadLocalDemo {
    private static ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new DateUtil("2019-11-25 09:00:" + i % 60));
        }
    }

    static class DateUtil implements Runnable {
        private String date;

        public DateUtil(String date) {
            this.date = date;
        }

        @Override
        public void run() {
            /**
             * �����ǰ�̲߳�����SimpleDateformat����ʵ����
             * ��ô���½�һ�����������õ���ǰ�߳��У�����Ѿ����У���ֱ��ʹ�á�
             */
            if (sdf.get() == null) {
                sdf.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            } else {
                try {
                    Date date = sdf.get().parse(this.date);
                    System.out.println(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            //����������˵��threadLocal�п��ܴ����ڴ�й©����ʹ����֮�����ʹ��remove��������������Ƴ���������ʹ�����ݿ�����һ������ʱ�ر����ӡ�
        }
    }
}