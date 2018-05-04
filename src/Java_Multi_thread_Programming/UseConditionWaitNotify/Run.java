package Java_Multi_thread_Programming.UseConditionWaitNotify;

public class Run {

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.start();
        Thread.sleep(5000);
        service.signal();
    }
}
