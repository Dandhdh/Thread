package Java_Multi_thread_Programming.ConditionTest;

public class ThreadA extends Thread {

    private MyService service;

    public ThreadA(MyService service){
        super();
        this.service = service;
    }


    @Override
    public void run(){
        for(int i=0; i< Integer.MAX_VALUE; i++){
            service.set();
        }
    }

}
