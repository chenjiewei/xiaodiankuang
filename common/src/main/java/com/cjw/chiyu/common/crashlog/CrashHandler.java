package com.cjw.chiyu.common.crashlog;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import com.cjw.chiyu.common.config.ChiYuConstants;
import com.cjw.chiyu.common.log.LogPoxy;
import com.cjw.chiyu.common.log.LogUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:59
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description: crash日志处理类
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    // 单例对象
    private static CrashHandler INSTANCE = new CrashHandler();
    // 日志tag
    public static final String TAG = "CrashHandler";
    // 显示时间格式
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    // 存储设备参数信息集合
    private Map<String, String> infos = new HashMap();
    // 上下文
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * 收集设备参数信息
     *
     * @param paramContext
     */
    private void collectDeviceInfo(Context paramContext) {
        try {
            PackageManager pm = paramContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(paramContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            LogPoxy.e(TAG, "NameNotFoundException", e.toString());
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (IllegalArgumentException e) {
                LogPoxy.e("CrashHandler.NameNotFoundException---> an error occured when collect crash info", e.toString());
            } catch (IllegalAccessException e) {
                LogPoxy.e("CrashHandler.NameNotFoundException---> an error occured when collect crash info", e.toString());
            }
        }
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作
     *
     * @param paramThrowable
     * @return
     */
    private boolean handleException(Throwable paramThrowable) {
        if (paramThrowable == null) {
            return false;
        }
        LogPoxy.e(TAG, paramThrowable.toString());
        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(CrashHandler.this.mContext, ChiYuConstants.CRASH_PROMPT, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        // 收集设备参数信息
        collectDeviceInfo(mContext);
        // 保存异常到文件中
        saveCrashInfo2File(paramThrowable);
        return true;
    }

    /**
     * 保存错误日志到文件中
     *
     * @param paramThrowable
     * @return 返回文件名称, 可以将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable paramThrowable) {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter((Writer) writer);
        paramThrowable.printStackTrace((PrintWriter) printWriter);
        Throwable cause = paramThrowable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long currentTime = System.currentTimeMillis();
            String time = this.formatter.format(new Date());
            String fileName = "crash-" + time + "-" + currentTime + ".log";
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ChiYuConstants.CRASH_LOG_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(new File(dir, fileName));
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (FileNotFoundException e) {
            LogPoxy.e(TAG, e.toString());
        } catch (IOException ex) {
            LogPoxy.e(TAG, ex.toString());
        } finally {
            this.infos = new HashMap();
        }
        return null;
    }

    /**
     * 初始化参数
     *
     * @param paramContext
     */
    public void init(Context paramContext) {
        this.mContext = paramContext;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     *
     * @param paramThread
     * @param paramThrowable
     */
    public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
        if ((!handleException(paramThrowable)) && (this.mDefaultHandler != null)) {
            this.mDefaultHandler.uncaughtException(paramThread, paramThrowable);
            return;
        }
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException ex) {
            LogPoxy.e(TAG, ex.toString());
        }
        //退出程序
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    /**
     * 私有构造方法
     */
    private CrashHandler() {
    }


}

