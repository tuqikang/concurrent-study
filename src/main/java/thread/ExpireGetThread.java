package thread;

import javax.xml.transform.Result;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-22 20:12
 *
 * 等待超时模式
 */
public class ExpireGetThread {

    private String result;

    public synchronized Object get(long mills) {
        //过期时间
        long future = System.currentTimeMillis()+mills;

        //等待时间
        long remaining = mills;

        while ((result==null)&&remaining>0){
            try {
                wait(remaining);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //每次都算 还要等待多久 如果超过时间 直接返回默认result
            remaining = future-System.currentTimeMillis();
        }

        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        ExpireGetThread getThread = new ExpireGetThread();

        Thread t1 = new Thread(()->{
            getThread.get(3000);
        });
        t1.start();

        t1.join();

    }
}
