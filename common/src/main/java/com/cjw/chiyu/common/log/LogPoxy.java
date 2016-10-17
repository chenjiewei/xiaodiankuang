package com.cjw.chiyu.common.log;

import android.content.Context;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:00
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:日志调用类
 */
public class LogPoxy {
    /**
     * D级log
     *
     * @param tag
     * @param paramVarArgs
     */
    public static void d(String tag, String... paramVarArgs) {
        synchronized (LogPoxy.class) {
            LogUtils.getInstances().d(tag, paramVarArgs);
            return;
        }
    }

    /**
     * E级log
     *
     * @param tag
     * @param paramVarArgs
     */
    public static void e(String tag, String... paramVarArgs) {
        synchronized (LogPoxy.class) {
            LogUtils.getInstances().e(tag, paramVarArgs);
            return;
        }
    }

    /**
     * I级log
     *
     * @param tag
     * @param paramVarArgs
     */
    public static void i(String tag, String... paramVarArgs) {
        synchronized (LogPoxy.class) {
            LogUtils.getInstances().i(tag, paramVarArgs);
            return;
        }
    }

    /**
     * V级log
     *
     * @param tag
     * @param paramVarArgs
     */
    public static void v(String tag, String... paramVarArgs) {
        synchronized (LogPoxy.class) {
            LogUtils.getInstances().v(tag, paramVarArgs);
            return;
        }
    }

    /**
     * W级log
     *
     * @param tag
     * @param paramVarArgs
     */
    public static void w(String tag, String... paramVarArgs) {
        synchronized (LogPoxy.class) {
            LogUtils.getInstances().w(tag, paramVarArgs);
            return;
        }
    }

    /**
     * 初始化log需要的数据
     *
     * @param paramInt     log等级
     * @param paramBoolean 是否保存log
     * @param paramContext 上下文
     */
    public static void initLogLever(int paramInt, boolean paramBoolean, Context paramContext) {
        LogUtils.getInstances().setCurLogLevel(paramInt);
        LogUtils.getInstances().setIsSaveLog(paramContext, paramBoolean);
    }
}
