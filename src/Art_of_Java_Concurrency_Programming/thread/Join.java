package Art_of_Java_Concurrency_Programming.thread;

public class Join {

    public static void main(String[] args) {
        Thread previous = Thread.currentThread();
        for(int i=0; i<10; i++){
            //ÿһ���߳�ӵ��ǰһ���̵߳����ã���Ҫ�ȴ�ǰһ���߳���ֹ�����ܴӵȴ��з���
            Thread thread = new Thread(new Domino(previous),String.valueOf(i));
            thread.start();
            previous = thread;
        }
    }

    static class Domino implements Runnable {
        private Thread thread;
        public Domino(Thread thread){
            this.thread = thread;
        }
        @Override
        public void run(){
            try{
                thread.join();
            }catch (InterruptedException e){}
            System.out.println(Thread.currentThread().getName() + " terminate");
        }
    }
}
