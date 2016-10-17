package com.cjw.chiyu.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:05
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */

public class CheckNet {
    /**
     * 检查当前是否连上了WIFI
     *
     * @param paramContext
     * @return
     */
    public static boolean checkIsWifi(Context paramContext) {
        boolean bool = false;
        if (((TelephonyManager) paramContext.getSystemService(Context.TELEPHONY_SERVICE)).getNetworkType() == 1) {
            bool = true;
        }
        return bool;
    }

    /**
     * 判断当前网络是否有用
     *
     * @param paramContext
     * @return
     */
    public static boolean isNetworkConnected(Context paramContext) {
        NetworkInfo connectivityManager = ((ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return (connectivityManager != null) && (connectivityManager.isConnectedOrConnecting());
    }
}
