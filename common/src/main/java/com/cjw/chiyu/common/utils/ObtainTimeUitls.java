package com.cjw.chiyu.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:07
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description: 获取时间工具类
 */
public class ObtainTimeUitls {
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String obtainCurrentTime() {
        return df.format(new Date());
    }

    /**
     * 获取当前时间的半天前的时间戳
     *
     * @return
     */
    public static String obtainHalfDayTime() {
        return String.valueOf(System.currentTimeMillis() - 12 * 60 * 60 * 1000L).substring(0, 10);
    }
}
