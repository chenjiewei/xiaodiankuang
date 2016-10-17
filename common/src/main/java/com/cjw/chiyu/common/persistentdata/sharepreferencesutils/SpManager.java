package com.cjw.chiyu.common.persistentdata.sharepreferencesutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.cjw.chiyu.common.config.ChiYuConstants;
import com.cjw.chiyu.common.log.LogPoxy;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:02
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description: sp管理类
 */
public class SpManager {
    private static final String TAG = "SpManager";
    private static SpManager mSpManager = null;
    private Context mContext = null;
    private Handler mHandler = null;
    private SharedPreferences mSharedPreference = null;

    /**
     * DCL双层检查，单例获取
     *
     * @return
     */
    public static SpManager getNewInstance() {
        if (mSpManager == null) {
            synchronized (SpManager.class) {
                if (mSpManager == null) {
                    mSpManager = new SpManager();
                }
            }
        }
        return mSpManager;
    }

    /**
     * 获取布尔类型的值
     *
     * @param paramString
     * @return
     */
    public boolean gainBooleanValue(String paramString) {
        return this.mSharedPreference.getBoolean(paramString, false);
    }

    /**
     * 获取整型的值
     *
     * @param paramString
     * @return
     */
    public int gainIntValue(String paramString) {
        return this.mSharedPreference.getInt(paramString, -1);
    }

    /**
     * 获取字符串型的值
     *
     * @param paramString
     * @return
     */
    public String gainStringValue(String paramString) {
        return this.mSharedPreference.getString(paramString, "");
    }

    /**
     * 初始化布尔配置数据
     *
     * @param paramContext
     */
    public void initSputils(Context paramContext) {
        this.mContext = paramContext;
        if (this.mContext == null) {
            LogPoxy.e(TAG, "mContext == null");
            return;
        }
        this.mSharedPreference = this.mContext.getSharedPreferences(ChiYuConstants.APP_SP_FILE_NAME, Context.MODE_PRIVATE);
        this.mHandler = new Handler();
    }

    /**
     * 保存布尔值
     *
     * @param paramString
     * @param paramBoolean
     */
    public void saveBoolean(final String paramString, final boolean paramBoolean) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (SpManager.this.mSharedPreference == null) {
                    LogPoxy.e(TAG, "mSharedPreference == null");
                    return;
                }
                SharedPreferences.Editor localEditor = SpManager.this.mSharedPreference.edit();
                localEditor.putBoolean(paramString, paramBoolean);
                localEditor.apply();
            }
        });
    }

    /**
     * 保存整型数据
     *
     * @param paramString
     * @param paramInt
     */
    public void saveInt(final String paramString, final int paramInt) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (SpManager.this.mSharedPreference == null) {
                    LogPoxy.e(TAG, "mSharedPreference == null");
                    return;
                }
                SharedPreferences.Editor localEditor = SpManager.this.mSharedPreference.edit();
                localEditor.putInt(paramString, paramInt);
                localEditor.apply();
            }
        });
    }

    /**
     * 保存字符串数据
     *
     * @param paramString1
     * @param paramString2
     */
    public void saveString(final String paramString1, final String paramString2) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (SpManager.this.mSharedPreference == null) {
                    LogPoxy.e(TAG, "mSharedPreference == null");
                    return;
                }
                SharedPreferences.Editor localEditor = SpManager.this.mSharedPreference.edit();
                localEditor.putString(paramString1, paramString2);
                localEditor.apply();
            }
        });
    }
}
