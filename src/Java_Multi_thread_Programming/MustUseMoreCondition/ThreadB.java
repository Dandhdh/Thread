package Java_Multi_thread_Programming.MustUseMoreCondition;


public class ThreadB extends Thread {

    private MyService service;

    public ThreadB(MyService service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        service.awaitB();
    }

}
