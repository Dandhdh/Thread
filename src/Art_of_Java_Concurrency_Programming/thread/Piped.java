package Art_of_Java_Concurrency_Programming.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 对于Piped类型的流，必须要进行绑定，也就是调用connect（）方法，
 * 如果没有将输入/输出绑定起来，对于该流的访问将抛出异常
 */
public class Piped {

    public static void main(String[] args) throws Exception {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        //将输出流和输入流进行连接，否则在使用时会抛出 IOException 异常
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
