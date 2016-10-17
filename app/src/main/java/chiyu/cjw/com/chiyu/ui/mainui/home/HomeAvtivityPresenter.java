package chiyu.cjw.com.chiyu.ui.mainui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import chiyu.cjw.com.chiyu.ui.base.BasePresenter;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:49
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeAvtivityPresenter extends BasePresenter<IHomeActivityView> implements IHomeAvtivityPresenter {
    private Context mContext = null;

    public HomeAvtivityPresenter(Context paramContext) {
        this.mContext = paramContext;
    }

    public void updateUserInfo() {
        Bitmap localBitmap = BitmapFactory.decodeResource(this.mContext.getResources(), 2130903040);
        ((IHomeActivityView) this.mView).showUserInfo("chenjiewei", "http://blog.csdn.net/u010392352/", localBitmap);
    }

    public void validateCredentials() {
        if (0 != 0) {
            ((IHomeActivityView) this.mView).toUserActivity();
            return;
        }
        ((IHomeActivityView) this.mView).toLoginActivity();
    }
}

