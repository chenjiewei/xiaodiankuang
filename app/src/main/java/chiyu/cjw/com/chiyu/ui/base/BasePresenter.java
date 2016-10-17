package chiyu.cjw.com.chiyu.ui.base;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:02
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public abstract class BasePresenter<V> {
    protected V mView = null;

    protected void attach(V paramV) {
        this.mView = paramV;
    }

    protected void dettach() {
        this.mView = null;
    }
}
