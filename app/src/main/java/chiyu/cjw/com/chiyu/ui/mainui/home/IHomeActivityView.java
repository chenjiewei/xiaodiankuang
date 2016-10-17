package chiyu.cjw.com.chiyu.ui.mainui.home;

import android.graphics.Bitmap;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:50
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public interface IHomeActivityView {
    void showUserInfo(String paramString1, String paramString2, Bitmap paramBitmap);

    void toLoginActivity();

    void toUserActivity();
}

