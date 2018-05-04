package Java_Multi_thread_Programming.ConditionTest;

public class ThreadB extends Thread {

    private MyService service;

    public ThreadB(MyService service){
        super();
        this.service = service;
    }

    @Override
    public void run(){
        for(int i=0; i< Integer.MAX_VALUE; i++){
            service.get();
        }
    }

}
