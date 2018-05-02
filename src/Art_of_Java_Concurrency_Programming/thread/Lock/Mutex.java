package Art_of_Java_Concurrency_Programming.thread.Lock;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//�Զ������--��ռʽ�� Mutex
public class Mutex implements Lock{

    // ����Ҫ����������Sync�ϼ���
    private final Sync sync = new Sync();
    @Override
    public void lock() { sync.acquire(1); }
    @Override
    public boolean tryLock() { return sync.tryAcquire(1); }
    @Override
    public void unlock() { sync.release(1); }
    @Override
    public Condition newCondition() { return sync.newCondition(); }
    public boolean isLocked() { return sync.isHeldExclusively(); }
    public boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }
    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    // ��̬�ڲ��࣬�Զ���ͬ����
    //���ڲ���̳���ͬ������ʵ���˶�ռʽ��ȡ���ͷ�ͬ��״̬
    private static class Sync extends AbstractQueuedSynchronizer {

        // �Ƿ���ռ��״̬
        // state==1�Ǵ�����ռ��״̬
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // ��״̬Ϊ0��ʱ���ȡ��
        @Override
        public boolean tryAcquire(int acquires) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
            return false;
        }

        // �ͷ�������״̬����Ϊ0
        @Override
        protected boolean tryRelease(int releases) {

            if (getState() == 0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // ����һ��Condition��ÿ��condition��������һ��condition����
        Condition newCondition() {
            return new ConditionObject();
        }

    }

}
