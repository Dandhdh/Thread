package Art_of_Java_Concurrency_Programming.thread;

public class DeadLock {

  public static void main(String[] args) {

    new Thread(() -> {
      try {
         new DeadLock().resource1();
      } catch (InterruptedException e) {
      }
    }
    ).start();

    new Thread(() -> {
      try {
         new DeadLock().resource2();
      } catch (InterruptedException e) {
      }
    }
    ).start();
  }

  void resource1() throws InterruptedException {

    synchronized ("resource1") {
      System.out.println("��ȡ��Դ1");
       // �ȴ� 1 ������һ���߳��õ���
      Thread.sleep(1000);
      resource2();
    }


  }

  void resource2() throws InterruptedException {
    synchronized ("resource2") {
      System.out.println("��ȡ��Դ2");
      // �ȴ� 1 ������һ���߳��õ���
      Thread.sleep(1000);
      resource1();
    }
  }
}