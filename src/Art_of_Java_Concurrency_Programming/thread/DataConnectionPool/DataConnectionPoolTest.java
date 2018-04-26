package Art_of_Java_Concurrency_Programming.thread.DataConnectionPool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class DataConnectionPoolTest {

    static DataConnectionPool pool = new DataConnectionPool(10);

    //��֤����ConnectionRunner��ͬʱ��ʼ
    static CountDownLatch start = new CountDownLatch(1);

    //main�߳̽���ȴ����е�ConnectionRunner�������ܼ���ִ��
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        //�߳����������޸��߳��������й۲�
        int threadCount = 10;
        end = new CountDownLatch(threadCount);

        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for(int i=0; i<threadCount; i++){
            Thread thread = new Thread(new ConnectionRunner(count,got,notGot),"ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: "+(threadCount * count));
        System.out.println("got connection: "+got);
        System.out.println("not got connection: "+notGot);
    }

    static class ConnectionRunner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot){
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run(){
            try{
                start.await();
            }catch (Exception e){}
            while (count>0){
                try{
                    //���̳߳��л�ȡ���ӣ����1000ms���޷���ã�������null
                    //�ֱ�ͳ�����ӻ�ȡ������got��δȡ��������notGot
                    Connection connection = pool.fetchConnection(1000);
                    if(connection!=null){
                        try{
                            connection.createStatement();
                            connection.commit();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else {
                        notGot.incrementAndGet();
                    }
                }catch (Exception e){
                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

}
