package chiyu.cjw.com.chiyu.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import chiyu.cjw.com.chiyu.ui.eventbus.EventBusBean;

/**
 * Created: chnejiewei
 * data: 2016/9/10 19:58
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description: fragment基类
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>>
        extends Fragment {
    protected View mContentView;
    protected Context mContext = null;
    protected BaseViewBean mIViewBean = null;
    protected T mPresenter = null;

    protected abstract void eventBusThread(EventBusBean paramEventBusBean);

    protected abstract int initLayout();

    protected abstract void initLogicObject();

    protected abstract void initPresenter();

    protected abstract BaseViewBean initViewBean();

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        this.mContext = getContext();
        this.mContentView = paramLayoutInflater.inflate(initLayout(), paramViewGroup, false);
        initLogicObject();
        this.mIViewBean = initViewBean();
        initPresenter();
        if (this.mPresenter != null) {
            this.mPresenter.attach((V) this);
        }
        EventBus.getDefault().register(this);
        return this.mContentView;
    }

    public void onDestroy() {
        this.mPresenter.dettach();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    @Subscribe
    public void onEventMainThread(EventBusBean paramEventBusBean) {
        eventBusThread(paramEventBusBean);
    }
    @Subscribe
    protected void sendEventBusMsg(String paramString) {
        EventBus.getDefault().post(new EventBusBean(paramString));
    }

    protected void startActivityWithExtras(Class<?> paramClass, Bundle paramBundle) {
        Intent intent = new Intent(this.mContext, paramClass);
        intent.putExtras(paramBundle);
        startActivity(intent);
    }

    protected void startActivityWithOutExtras(Class<?> paramClass) {
        startActivity(new Intent(this.mContext, paramClass));
    }
}

