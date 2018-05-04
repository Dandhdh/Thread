package Java_Multi_thread_Programming.ReentrantLockTest;

public class ThreadA extends Thread {

    private MyService service;

    public ThreadA(MyService service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.methodA();
    }

}
