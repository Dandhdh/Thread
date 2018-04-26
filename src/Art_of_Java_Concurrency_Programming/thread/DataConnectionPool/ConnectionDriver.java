package Art_of_Java_Concurrency_Programming.thread.DataConnectionPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * 由于java.sql.Connection是一个接口，最终实现是由数据库驱动提供方来实现的
 * 所以通过动态代理构造一个Connection
 */
public class ConnectionDriver {

    static class ConnectionHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args)throws  Throwable{
            if(method.getName().equals("commit")){
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    //创建一个Connection代理，在commit时休眠100毫秒
    public static final Connection createConnection(){
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[]{Connection.class}, new ConnectionHandler());
    }

}
