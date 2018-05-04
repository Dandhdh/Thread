package Java_Multi_thread_Programming.ReentrantLockTest;

public class Run {

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("a");
        a.start();
        ThreadAA aa = new ThreadAA(service);
        aa.setName("aa");
        aa.start();

    }

}
