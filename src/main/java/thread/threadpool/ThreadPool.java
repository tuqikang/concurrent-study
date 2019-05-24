package thread.threadpool;

/**
 * ___________ ________     ____  __.
 * \__    ___/ \_____  \   |    |/ _|
 * |    |     /  / \  \  |      <
 * |    |    /   \_/.  \ |    |  \
 * |____|    \_____\ \_/ |____|__ \
 *
 * @Author: tuqikang
 * @Date: 2019-05-24 08:37
 */
public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个任务
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作线程
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少工作线程
     * @param num
     */
    void removeWorkers(int num);

    /**
     * 得到正在等待执行的任务数量
     * @return
     */
    int getJobSize();
}
