package Art_of_Java_Concurrency_Programming.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

//使用JMX来查看一个普通java程序包含哪些线程
public class MultiTread {

    public static void main(String[] args) {

        //获取java线程管理器MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        //遍历线程信息，仅打印线程ID的线程名称的信息
        for(ThreadInfo threadInfo : threadInfos){
            System.out.println("["+threadInfo.getThreadId()+"]"+threadInfo.getThreadName());
        }
    }

}
