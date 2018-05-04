package Java_Multi_thread_Programming.ReentrantLockTest;

public class ThreadAA extends Thread {

    private MyService service;

    public ThreadAA(MyService service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.methodA();
    }
}
