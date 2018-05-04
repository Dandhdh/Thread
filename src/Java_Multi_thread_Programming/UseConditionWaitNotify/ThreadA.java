package Java_Multi_thread_Programming.UseConditionWaitNotify;


public class ThreadA extends Thread {

    private MyService service;

    public ThreadA(MyService service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.await();
    }

}
