package Art_of_Java_Concurrency_Programming.thread.ThreadPool;

public interface ThreadPool<Job extends Runnable>{

    //ִ��һ��Job�����Job��Ҫʵ��Runnable
    void execute(Job job);

    //�ر��߳�
    void shutdown();

    //���ӹ������߳�
    void addWorkers(int num);

    //���ٹ������߳�
    void removeWorkers(int num);

    //�õ����ڵȴ�ִ�е����������
    int getJobSize();

}
