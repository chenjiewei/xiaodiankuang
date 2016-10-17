package chiyu.cjw.com.chiyu.ui.uiutils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:05
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class ToastUilts {
    private static final String TAG = "ToastUilts";
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static Toast toast = null;

    /**
     * 显示toast的时间长度类型
     */
    public enum TOAST_TYPE {
        TOAST_TYPE_SHORT_TIME, TOAST_TYPE_LONG_TIME;
    }

    /**
     * 取消Toast
     */
    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * 显示短时间的TOAST显示
     *
     * @param paramContext
     * @param paramString
     */
    public static void showMessage(Context paramContext, String paramString) {
        showMessage(paramContext, paramString, TOAST_TYPE.TOAST_TYPE_SHORT_TIME);
    }

    /**
     * 调用显示toast
     *
     * @param paramContext
     * @param paramString
     * @param toast_type
     */
    public static void showMessage(Context paramContext, String paramString, TOAST_TYPE toast_type) {
        showMessageDetail(paramContext, paramString, toast_type);
    }

    /**
     * 显示Toast的实现细节
     *
     * @param paramContext
     * @param paramString
     * @param paramInt
     */
    private static synchronized void showMessageDetail(final Context paramContext, final String paramString, final TOAST_TYPE paramInt) {
        ToastUilts.mHandler.post(new Runnable() {
            public void run() {
                if (paramInt == TOAST_TYPE.TOAST_TYPE_SHORT_TIME) {
                    toast = Toast.makeText(paramContext, paramString, Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(paramContext, paramString, Toast.LENGTH_LONG);
                }
                toast.show();
                toast = null;
            }
        });
    }
}



