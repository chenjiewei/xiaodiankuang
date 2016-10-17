package com.cjw.chiyu.data.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:56
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class ThreadPoolUtils {
    private static ThreadPoolUtils mThreadPoolUtils = null;
    // 获取JVM处理器的个数,目前线程池活跃线程最好大于3个
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 3);
    // 获取单例对象
    public static ThreadPoolUtils newInstances() {
        if (mThreadPoolUtils == null) {
            synchronized (ThreadPoolUtils.class) {
                if (mThreadPoolUtils == null) {
                    mThreadPoolUtils = new ThreadPoolUtils();
                }
            }
        }
        return mThreadPoolUtils;
    }

    private ThreadPoolUtils() {
    }

    public void submitRunTask(Runnable paramRunnable) {
        this.mExecutorService.submit(paramRunnable);
    }
}
