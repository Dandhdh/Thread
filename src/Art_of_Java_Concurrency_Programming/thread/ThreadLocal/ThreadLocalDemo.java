package Art_of_Java_Concurrency_Programming.thread.ThreadLocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 不是用来解决共享对象的多线程访问问题的，
 * 数据实质上是放在每个thread实例引用的threadLocalMap,
 * 也就是说每个不同的线程都拥有专属于自己的数据容器（threadLocalMap），彼此不影响。
 *
 * 因此threadLocal只适用于 共享对象会造成线程安全 的业务场景。
 * 比如
 * hibernate中通过threadLocal管理Session就是一个典型的案例，
 * 不同的请求线程（用户）拥有自己的session,若将session共享出去被多线程访问，
 * 必然会带来线程安全问题。
 *
 * 一个例子，SimpleDateFormat.parse方法会有线程安全的问题，
 * 我们可以尝试使用threadLocal包装SimpleDateFormat，将该实例不被多线程共享即可。
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
             * 如果当前线程不持有SimpleDateformat对象实例，
             * 那么就新建一个并把它设置到当前线程中，如果已经持有，就直接使用。
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

            //在上面我们说过threadLocal有可能存在内存泄漏，在使用完之后，最好使用remove方法将这个变量移除，就像在使用数据库连接一样，及时关闭连接。
        }
    }
}