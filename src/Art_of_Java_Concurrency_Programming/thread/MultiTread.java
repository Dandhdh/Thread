package Art_of_Java_Concurrency_Programming.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

//ʹ��JMX���鿴һ����ͨjava���������Щ�߳�
public class MultiTread {

    public static void main(String[] args) {

        //��ȡjava�̹߳�����MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //����Ҫ��ȡͬ����monitor��synchronizer��Ϣ������ȡ�̺߳��̶߳�ջ��Ϣ
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        //�����߳���Ϣ������ӡ�߳�ID���߳����Ƶ���Ϣ
        for(ThreadInfo threadInfo : threadInfos){
            System.out.println("["+threadInfo.getThreadId()+"]"+threadInfo.getThreadName());
        }
    }

}
