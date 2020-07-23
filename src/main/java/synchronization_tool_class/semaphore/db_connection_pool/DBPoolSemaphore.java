package synchronization_tool_class.semaphore.db_connection_pool;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 *类说明：演示Semaphore用法，一个数据库连接池的实现
 */
public class DBPoolSemaphore {

    private final static int POOL_SIZE = 10;
    private final Semaphore useful,useless;//useful表示可用的数据库连接，useless表示已用的数据库连接

    public DBPoolSemaphore() {
        this. useful = new Semaphore(POOL_SIZE);
        this.useless = new Semaphore(0);
    }

    //存放数据库连接的容器
    private static LinkedList<Connection> pool = new LinkedList<Connection>();
    //初始化池.
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());
        }
    }

    /*归还连接*/
    public void returnConnect(Connection connection) throws InterruptedException {
        if(connection!=null) {
            System.out.println("Thread_"+Thread.currentThread().getId()+"在尝试归还；"+"当前有"+useful.getQueueLength()+"个线程等待数据库连接！！"
                    +"可用连接数:"+useful.availablePermits());
            //获取等待获取信号量的线程数目int getQueueLength();
            //返回当前可用的信号量数目，此方法通常用于调试int availablePermits();
            useless.acquire();
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();
        }
    }

    /*从池子拿连接*/
    public Connection takeConnect() throws InterruptedException {
        //拿连接，如果拿不到连接，那么线程会被阻塞在此
        useful.acquire();
        Connection conn;
        synchronized (pool) {
            conn = pool.removeFirst();
        }
        useless.release();
        return conn;
    }

}
