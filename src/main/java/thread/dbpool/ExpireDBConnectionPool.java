package thread.dbpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-22 20:20
 * 假设1000毫秒内如果无法获取可用连接，将返回一个null
 */
public class ExpireDBConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    //初始化池子中的连接数量
    public ExpireDBConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    //放回池子并且通知其他消费者（线程）
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                //连接释放后需要进行通知，这样其他消费者能够感知连接池已经归还一个连接
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 在mills无法取得连接，将会返回null
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            //连接超时
            if (mills<=0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis()+mills;
                long remaining = mills;
                while (pool.isEmpty()&&remaining>0){
                    pool.wait(remaining);
                    remaining = future-System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
