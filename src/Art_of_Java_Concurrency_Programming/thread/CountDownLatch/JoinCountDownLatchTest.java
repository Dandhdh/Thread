package Art_of_Java_Concurrency_Programming.thread.CountDownLatch;

//一个线程等待多个线程完成
//使用join：当前的线程等待执行join方法的线程执行完
public class JoinCountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() { System.out.println("parser1 finish");
            }
        });
        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
System.out.println("parser2 finish");
}
        });
        parser1.start();
        parser2.start();
        parser2.join();
        parser1.join();
        System.out.println("all parser finish");
    }
}