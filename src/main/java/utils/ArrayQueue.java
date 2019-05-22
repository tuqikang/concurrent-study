package utils;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-21 21:28
 * 自己实现一个阻塞队列
 * <p>
 * 阻塞队列的特点
 * 1.基本队列特性：先进先出。
 * 2.写入队列空间不可用时会阻塞。
 * 3.获取队列数据时当队列为空时将阻塞。
 */
public class ArrayQueue<T> {

    /**
     * 队列数量
     */
    private volatile int count = 0;

    /**
     * 数据存储
     */
    private Object[] items;

    /**
     * 队列满时的阻塞锁
     */
    private Object full = new Object();

    /**
     * 队列空时的阻塞锁
     */
    private Object empty = new Object();

    /**
     * 写入数据时的下标
     */
    private volatile int putIndex;

    /**
     * 获取数据时的下标
     */
    private volatile int getIndex;

    public ArrayQueue(int size) {
        items = new Object[size];
    }

    /**
     * 从队尾写入数据
     *
     * @param t
     */
    public void put(T t) {
        synchronized (full) {
            while (count == items.length) {
                try {
                    full.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        synchronized (empty) {
            //写入数据
            items[putIndex] = t;
            count++;

            putIndex++;
            if (putIndex == items.length) {
                putIndex = 0;
            }

            empty.notifyAll();
        }
    }

    /**
     * 消费数据
     *
     * @return
     */
    public T get() {
        synchronized (empty) {
            while (count == 0) {
                try {
                    empty.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
        }

        synchronized (full) {
            Object result = items[getIndex];
            items[getIndex] = null;
            count--;

            getIndex++;
            if (getIndex == items.length) {
                getIndex = 0;
            }

            full.notifyAll();

            return (T) result;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayQueue<String> queue = new ArrayQueue<>(299);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                queue.put(i+"");
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                queue.put(i+"");
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                queue.put(i+"");
            }
        });

        Thread t4= new Thread(()->{
            System.out.println(queue.get());
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(queue.count);
    }
}
