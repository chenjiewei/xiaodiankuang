package chiyu.cjw.com.chiyu.ui.base;

import android.view.View;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:02
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public abstract class BaseViewBean {
    protected View rootView = null;

    protected BaseViewBean(View paramView) {
        this.rootView = paramView;
    }

    protected <T extends View> T $$(int viewId) {
        return (T) this.rootView.findViewById(viewId);
    }

    protected abstract ViewBeanGen gainViewBeanGen();

    protected abstract View getBackView();
}