package thread.local;

import java.util.concurrent.TimeUnit;

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
 * @date ：Created in 2019-06-08 13:52
 * @description：
 * @modified By：
 * @version:
 */
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t= new Thread();
        ThreadLocal<String> local1 = new ThreadLocal<>();
        local1.set("hello world");
//        local1.set("tuqikang");
        ThreadLocal<String> local2 = new ThreadLocal<>();
        local2.set("hello world 2");
        System.out.println("当前线程"+Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(10);
        System.out.println("从当前线程获得local存储的变量"+local1.get());
        System.out.println(local2.get());
        new Thread(()-> {
            local1.set("new  hello world");
            System.out.println("我是"+Thread.currentThread().getName()+",我要从local中获得它存储的对象:"+local1.get());
        },"新线程").start();
    }
}
