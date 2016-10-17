package chiyu.cjw.com.chiyu.application;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.cjw.chiyu.common.crashlog.CrashHandler;
import com.cjw.chiyu.common.log.LogPoxy;
import com.cjw.chiyu.common.persistentdata.sharepreferencesutils.SpManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import chiyu.cjw.com.chiyu.BuildConfig;

/**
 * des:default
 * Created:chnejiewei
 * data:2016/9/10 11:09
 * blog:http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 */
public class ChiYuApp extends Application {
    private RefWatcher mRefWatcher;
    private static final String DEBUG_TYPE_BUILD = "debug";

    public void onCreate() {
        super.onCreate();
        // 内存监控
        this.mRefWatcher = LeakCanary.install(this);
        //当前是debug版本就使用严苛模式
        if (BuildConfig.BUILD_TYPE.equals(DEBUG_TYPE_BUILD)) {
            UserStrictMode();
        }
        // log初始化
        LogPoxy.initLogLever(2, false, getApplicationContext());
        // 异常日志的初始化
        CrashHandler.getInstance().init(getApplicationContext());
        SpManager.getNewInstance().initSputils(getApplicationContext());
        new ChiYuApplicationInits().initApplication();
    }

    /**
     * 使用严苛模式
     */
    private void UserStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
    }
}
