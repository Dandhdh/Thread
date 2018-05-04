package Java_Multi_thread_Programming.ConditionTestManyToMany;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现生产者/消费者模式：一对一交替打印
 */
public class MyService {

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;

    public void set(){
        try{
            lock.lock();
            while (hasValue == true){
                System.out.println(" 有可能连续打印 ** ");
                condition.await();
            }
            System.out.println(" 打印 * ");
            hasValue = true;
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void get(){
        try{
            lock.lock();
            while (hasValue == false){
                System.out.println(" 有可能连续打印 $$ ");
                condition.await();
            }
            System.out.println(" 打印 $ ");
            hasValue = false;
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
