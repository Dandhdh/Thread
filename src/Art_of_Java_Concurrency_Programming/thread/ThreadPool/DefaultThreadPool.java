package Art_of_Java_Concurrency_Programming.thread.ThreadPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    //�̳߳ص����������
    private static final int MAX_WORKER_NUMBERS = 10;
    //�̳߳ص�Ĭ������
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    //�̳߳���С����
    private static final int MIN_WORKER_NUMBERS = 1;
    //����һ�������б���������������빤��
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    //�������б�
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    //�������߳�����
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    //�̱߳������
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool(){
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num){
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS :
                num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num ;
    }

    //��ʼ���̹߳�����
    private void initializeWorkers(int num){
        for(int i=0; i<num; i++){
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-Worker-"+threadNum.incrementAndGet());
            thread.start();
        }
    }

    //����һ������
    @Override
    public void execute(Job job){
        if(job!=null){
            //����һ��������Ȼ��֪ͨ
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown(){
        for(Worker worker : workers){
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num){
        synchronized (jobs){
            //����������Worker�������ܳ��������ֵ
            if(num+this.workerNum > MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorkers(int num){
        synchronized (jobs){
            if(num >= this.workerNum){
                throw new IllegalArgumentException("beyond workNum");
            }
            //���ո���������ֹͣWorker
            int count = 0;
            while (count<num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count--;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize(){
        return jobs.size();
    }



    //�����ߣ�������������
    class Worker implements Runnable{
        //�Ƿ���
        private volatile boolean running = true;

        @Override
        public void run(){
            while (running){
                Job job = null;
                synchronized (jobs){
                    //��������б��ǿյģ���ô��wait
                    while (jobs.isEmpty()){
                        try{
                            jobs.wait();
                        }catch (InterruptedException e){
                            //��֪���ⲿ��WorkerTHread���жϲ���������
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    //ȡ��һ��Job
                    job = jobs.removeFirst();
                }
                if(job!=null){
                    try{
                        job.run();
                    }catch (Exception ex){
                        //���Զ�Jobִ�е�Exception
                    }
                }
            }
        }

        public  void shutdown(){
            running = false;
        }
    }
}