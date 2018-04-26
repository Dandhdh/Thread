package Art_of_Java_Concurrency_Programming.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * ����Piped���͵���������Ҫ���а󶨣�Ҳ���ǵ���connect����������
 * ���û�н�����/��������������ڸ����ķ��ʽ��׳��쳣
 */
public class Piped {

    public static void main(String[] args) throws Exception {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        //����������������������ӣ�������ʹ��ʱ���׳� IOException �쳣
        out.connect(in);
        Thread prinThread = new Thread(new Print(in),"prinThread");
        prinThread.start();;
        int recceive = 0;
        try{
            while((recceive = System.in.read()) !=-1 ){
                out.write(recceive);
            }
        }finally {
            out.close();
        }

    }

    static class Print implements Runnable{
        private PipedReader in;
        public Print(PipedReader in){
            this.in = in;
        }
        @Override
        public void run(){
            int receive = 0;
            try{
                while ((receive = in.read()) != -1){
                    System.out.print((char) receive);
                }
            }catch (IOException e){}
        }
    }

}
