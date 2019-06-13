package thread.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

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
 * @date ：Created in 2019-06-13 16:22
 * @description：同一时刻至多两个线程同时访问，超过两个线程的访问都会被阻塞
 * @modified By：
 * @version:
 */
public class TwinsLock {

    private final Sync sync = new Sync(2);

    private static class Sync extends AbstractQueuedSynchronizer{
        public Sync(int count) {
            if (count<0){
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(2);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (;;){
                int current = getState();

                int newCount = current-reduceCount;
                if (newCount<0||compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (;;){
                int current = getState();
                int newCount = current+returnCount;
                if (compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }
    }

    public void lock(){
        sync.acquireShared(1);
    }

    public void unlock(){
        sync.releaseShared(1);
    }
}

