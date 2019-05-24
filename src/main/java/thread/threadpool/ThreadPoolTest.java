package thread.threadpool;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-24 09:07
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        DefaultThreadPool threadPool = new DefaultThreadPool();
        threadPool.execute(()->{
            System.out.println(Thread.currentThread().getName());
        }); threadPool.execute(()->{
            System.out.println(Thread.currentThread().getName());
        }); threadPool.execute(()->{
            System.out.println(Thread.currentThread().getName());
        }); threadPool.execute(()->{
            System.out.println(Thread.currentThread().getName());
        }); threadPool.execute(()->{
            System.out.println(Thread.currentThread().getName());
        }); threadPool.execute(()->{
            System.out.println(Thread.currentThread().getName());
        }); threadPool.execute(()->{
            System.out.println(Thread.currentThread().getName());
        });
    }
}
