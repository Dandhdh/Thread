#### CountDownLatch��CyclicBarrier�ıȽ�

###### CountDownLatch��CyclicBarrier�������ڿ��Ʋ����Ĺ����࣬����������ά���ľ���һ�������������������߻��Ǹ��в�ͬ���ص�ģ�

1. CountDownLatchһ������ĳ���߳�A�ȴ����ɸ������߳�ִ��������֮������ִ�У���CyclicBarrierһ������һ���̻߳���ȴ���ĳ��״̬��Ȼ����һ���߳���ͬʱִ�У�CountDownLatchǿ��һ���̵߳ȶ���߳����ĳ�����顣CyclicBarrier�Ƕ���̻߳��ȣ��ȴ�Ҷ���ɣ���Я�ֹ�����
2. ����CountDownLatch��countDown�����󣬵�ǰ�̲߳��������������������ִ�У�������CyclicBarrier��await��������������ǰ�̣߳�ֱ��CyclicBarrierָ�����߳�ȫ����������ָ�����ʱ�򣬲��ܼ�������ִ�У�
3. CountDownLatch�����Ƚ��٣������Ƚϼ򵥣���CyclicBarrier�ṩ�ķ������࣬�����ܹ�ͨ��getNumberWaiting()��isBroken()��Щ������ȡ��ǰ����̵߳�״̬������CyclicBarrier�Ĺ��췽�����Դ���barrierAction��ָ���������̶߳�����ʱִ�е�ҵ���ܣ�
4. CountDownLatch�ǲ��ܸ��õģ���CyclicLatch�ǿ��Ը��õġ�
