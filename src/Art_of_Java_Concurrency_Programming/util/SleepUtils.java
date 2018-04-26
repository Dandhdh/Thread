package Art_of_Java_Concurrency_Programming.util;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

    public static final void second(long seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e){

        }
    }
}
