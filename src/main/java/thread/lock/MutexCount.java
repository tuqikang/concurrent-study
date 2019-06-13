package thread.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * █████▒█      ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
 * ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
 * ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
 * ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
 * ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
 * ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
 * ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
 * ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
 * ░     ░ ░      ░  ░
 *
 * @author ：涂齐康
 * @date ：Created in 2019-06-13 14:23
 * @description：
 * @modified By：
 * @version:
 */
public class MutexCount {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(31);

    private static int a = 0;

    private static Mutex mutex = new Mutex();

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrNoMutex();
                }
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        cyclicBarrier.await();
        System.out.println("不加锁:a=" + a);
        a = 0;
        cyclicBarrier.reset();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrMutex();
                }
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        cyclicBarrier.await();
        System.out.println("加锁:a=" + a);


    }

    public static void incrNoMutex() {
        a++;
    }

    public static void incrMutex() {
        mutex.lock();
        a++;
        mutex.unlock();
    }


}
