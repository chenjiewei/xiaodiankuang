package com.cjw.chiyu.common.log;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.cjw.chiyu.common.utils.PermissionCheck;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:00
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:日志
 */
public class LogUtils {
    // 单例对象
    private static LogUtils mLogUtils = null;
    // log默认的TAG
    private static final String DEFAULT_CLASSNAME = "cjw";
    // 是否保存log到文件中
    private boolean isSaveLog = false;
    // 默认设置的log等级
    private int mCurLogLevel = 2;
    // log的最大长度
    private static final int LOG_MAX_LENGTH = 2000;
    // 写入权限
    private final String write_permission = "android.permission.WRITE_EXTERNAL_STORAGE";

    /**
     * 私有构造方法
     */
    private LogUtils() {
    }

    /**
     * 单例方法
     *
     * @return
     */
    public static LogUtils getInstances() {
        if (mLogUtils == null) {
            synchronized (LogUtils.class) {
                if (mLogUtils == null) {
                    mLogUtils = new LogUtils();
                }
            }
        }
        return mLogUtils;
    }

    /**
     * log信息是否为空
     * 判断
     *
     * @param paramString
     * @return
     */
    private boolean isLogMsgEmpty(String paramString) {
        boolean bool = false;
        if ((paramString == null) || (paramString.isEmpty())) {
            bool = true;
        }
        return bool;
    }

    /**
     * 处理log的显示，根据等级等信息来处理
     *
     * @param paramInt
     * @param parmValues
     * @return
     */
    private String logMsgDispose(int paramInt, String... parmValues) {
        String str = "";
        if (this.mCurLogLevel > paramInt) {
            return "";
        }
        if ((parmValues == null) || (parmValues.length == 0)) {
            return null;
        }
        StringBuilder localStringBuilder = new StringBuilder();
        if (parmValues != null) {
            int i = parmValues.length;
            paramInt = 0;
            while (paramInt < i) {
                localStringBuilder.append(parmValues[paramInt]);
                paramInt += 1;
            }
            str = localStringBuilder.toString();
        }
        if (str.length() > LOG_MAX_LENGTH) {
            str = str.substring(0, LOG_MAX_LENGTH);
        }
        return str;
    }

    /**
     * d级log
     *
     * @param tag
     * @param paramVarArgs
     */
    public void d(String tag, String... paramVarArgs) {
        if (tag == null || tag.length() == 0) {
            tag = DEFAULT_CLASSNAME;
        }
        String logValue = logMsgDispose(Log.DEBUG, paramVarArgs);
        if ((isLogMsgEmpty(logValue)) || (this.mCurLogLevel > Log.DEBUG)) {
            return;
        }
        Log.d(tag, logValue);
    }

    /**
     * e级的log
     *
     * @param tag
     * @param paramVarArgs
     */
    public void e(String tag, String... paramVarArgs) {
        if (tag == null || tag.length() == 0) {
            tag = DEFAULT_CLASSNAME;
        }
        String logValue = logMsgDispose(Log.ERROR, paramVarArgs);
        if ((isLogMsgEmpty(logValue)) || (this.mCurLogLevel > Log.ERROR)) {
            return;
        }
        Log.e(tag, logValue);
    }


    /**
     * i级log
     *
     * @param tag
     * @param paramVarArgs
     */
    public void i(String tag, String... paramVarArgs) {
        if (tag == null || tag.length() == 0) {
            tag = DEFAULT_CLASSNAME;
        }
        String logValue = logMsgDispose(Log.INFO, paramVarArgs);
        if ((isLogMsgEmpty(logValue)) || (this.mCurLogLevel > Log.INFO)) {
            return;
        }
        Log.i(tag, logValue);
    }

    /**
     * V级log
     *
     * @param tag
     * @param paramVarArgs
     */
    public void v(String tag, String... paramVarArgs) {
        if (tag == null || tag.length() == 0) {
            tag = DEFAULT_CLASSNAME;
        }
        String logValue = logMsgDispose(Log.VERBOSE, paramVarArgs);
        if ((isLogMsgEmpty(logValue)) || (this.mCurLogLevel > Log.VERBOSE)) {
            return;
        }
        Log.v(tag, logValue);
    }

    /**
     * w级log
     *
     * @param tag
     * @param paramVarArgs
     */
    public void w(String tag, String... paramVarArgs) {
        if (tag == null || tag.length() == 0) {
            tag = DEFAULT_CLASSNAME;
        }
        String logValue = logMsgDispose(Log.WARN, paramVarArgs);
        if ((isLogMsgEmpty(logValue)) || (this.mCurLogLevel > Log.WARN)) {
            return;
        }
        Log.w(tag, logValue);
    }

    public int getCurLogLevel() {
        return this.mCurLogLevel;
    }

    public boolean isSaveLog() {
        return this.isSaveLog;
    }

    /**
     * 设置log的等级
     *
     * @param paramInt
     */
    public void setCurLogLevel(int paramInt) {
        this.mCurLogLevel = paramInt;
    }

    /**
     * 设置是否保存日志
     *
     * @param paramContext
     * @param paramBoolean
     */
    public void setIsSaveLog(Context paramContext, boolean paramBoolean) {
        this.isSaveLog = paramBoolean;
        if (!PermissionCheck.checkHadPermission(paramContext, write_permission)) {
            this.isSaveLog = false;
        }
    }

}

