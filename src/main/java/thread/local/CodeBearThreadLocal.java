package thread.local;

import java.util.concurrent.ConcurrentHashMap;

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
 * @date ：Created in 2019-06-08 16:01
 * @description 自己写个ThreadLocal
 * @modified By：
 * @version:
 */
public class CodeBearThreadLocal<T> {

    private ConcurrentHashMap<Long,T> hashMap = new ConcurrentHashMap<>(16);

    public void set(T t){
        hashMap.put(Thread.currentThread().getId(),t);
    }

    public T get(){
        return hashMap.get(Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        CodeBearThreadLocal<String> codeBearThreadLocal = new CodeBearThreadLocal<>();
        codeBearThreadLocal.set("hello world");
        System.out.println(codeBearThreadLocal.get());
        new Thread(()-> System.out.println(codeBearThreadLocal.get())).start();
    }
}
