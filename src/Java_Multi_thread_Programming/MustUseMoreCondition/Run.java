package Java_Multi_thread_Programming.MustUseMoreCondition;

public class Run {

    public static void main(String[] args)throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        Thread.sleep(3000);
        //只有A线程被唤醒
        service.signalAll_A();

    }

}
