package chiyu.cjw.com.chiyu.ui.uiutils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:06
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class UiThreadPoolUtils {
    private static final String TAG = "UiThreadPoolUtils";
    private static volatile UiThreadPoolUtils mThreadPoolUtils = null;
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private UiThreadPoolUtils() {
    }

    /**
     * 单例实现
     *
     * @return
     */
    public static UiThreadPoolUtils newInstances() {
        if (mThreadPoolUtils == null) {
            synchronized (UiThreadPoolUtils.class) {
                if (mThreadPoolUtils == null) {
                    mThreadPoolUtils = new UiThreadPoolUtils();
                }
            }
        }
        return mThreadPoolUtils;
    }

    /**
     * 执行任务
     *
     * @param paramRunnable
     */
    public void executeRunTask(Runnable paramRunnable) {
        this.mExecutorService.submit(paramRunnable);
    }
}

