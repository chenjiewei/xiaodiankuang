package chiyu.cjw.com.chiyu.ui.mainui.interestingtools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.ui.base.BaseFragment;
import chiyu.cjw.com.chiyu.ui.base.BaseViewBean;
import chiyu.cjw.com.chiyu.ui.eventbus.EventBusBean;

/**
 * Created: chnejiewei
 * data: 2016/9/10 21:33
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeInterestingFragment extends BaseFragment {
    protected void eventBusThread(EventBusBean paramEventBusBean) {
    }

    protected int initLayout() {
        return R.layout.cy_home_interesting_fragment;
    }

    protected void initLogicObject() {
    }

    protected void initPresenter() {
    }

    protected BaseViewBean initViewBean() {
        return null;
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        return super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
    }
}
