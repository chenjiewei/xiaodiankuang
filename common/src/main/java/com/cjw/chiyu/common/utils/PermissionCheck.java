package com.cjw.chiyu.common.utils;

import android.content.Context;

import com.cjw.chiyu.common.log.LogPoxy;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:07
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class PermissionCheck {
    private static final String TAG = "PermissionCheck";

    /**
     * 检查当前是否有这个权限
     *
     * @param paramContext
     * @param paramString
     * @return
     */
    public static boolean checkHadPermission(Context paramContext, String paramString) {
        if (paramContext == null) {
            return false;
        }
        if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0) {
            LogPoxy.d(TAG, new String[]{"checkHadPermission()=true"});
            return true;
        }
        LogPoxy.d(TAG, new String[]{"checkHadPermission()=false"});
        return false;
    }
}