package chiyu.cjw.com.chiyu.ui.mainui.joke.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.ui.base.BaseFragment;
import chiyu.cjw.com.chiyu.ui.base.BaseViewBean;
import chiyu.cjw.com.chiyu.ui.eventbus.EventBusBean;
import chiyu.cjw.com.chiyu.ui.eventbus.EventBusConstant;
import chiyu.cjw.com.chiyu.ui.mainui.joke.pickjoke.HomeJokeFragmentPic;
import chiyu.cjw.com.chiyu.ui.mainui.joke.randomjoke.HomeJokeFragmentRandom;
import chiyu.cjw.com.chiyu.ui.mainui.joke.wordsjoke.HomeJokeFragmentWords;
import chiyu.cjw.com.chiyu.ui.uiconfig.UiConfigConstant;
import chiyu.cjw.com.chiyu.ui.uiutils.FragmentPagerAdapterUtils;
import chiyu.cjw.com.chiyu.ui.uiutils.ToastUilts;

/**
 * Created: chnejiewei
 * data: 2016/9/10 21:55
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokeFragment extends BaseFragment<IHomeJokeFragmentView, HomeJokeFragmentPresenter> implements IHomeJokeFragmentView {
    private HomeJokeFragmentViewHolder mHomeJokeFragmentViewHolder = null;

    private void initView() {
        this.mHomeJokeFragmentViewHolder.home_joke_id_toolbar.setTitle(UiConfigConstant.HOME_FRAGMENT_JOKE_TOOLBAR_INTRO);
        this.mHomeJokeFragmentViewHolder.home_joke_id_toolbar.inflateMenu(R.menu.cy_home_joke_fragment_toolbar_menu);
        this.mHomeJokeFragmentViewHolder.home_joke_id_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                HomeJokeFragment.this.sendEventBusMsg(EventBusConstant.EVENT_BUS_NAVIGATION_VIEW_SWITCH_OPEN);
            }
        });
        this.mHomeJokeFragmentViewHolder.home_joke_id_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_search:
                        HomeJokeFragment.this.showSearchPage();
                        break;
                }
                return true;
            }
        });
        FragmentPagerAdapterUtils fragmentPagerAdapterUtils = new FragmentPagerAdapterUtils(getActivity().getSupportFragmentManager(), this.mContext);
        ArrayList<Fragment> fragment_list = new ArrayList();
        fragment_list.add(new HomeJokeFragmentRandom());
        fragment_list.add(new HomeJokeFragmentWords());
        fragment_list.add(new HomeJokeFragmentPic());
        fragmentPagerAdapterUtils.initData(UiConfigConstant.joke_tab_title, fragment_list);
        this.mHomeJokeFragmentViewHolder.home_joke_id_content.setOffscreenPageLimit(fragment_list.size());
        this.mHomeJokeFragmentViewHolder.home_joke_id_content.setAdapter(fragmentPagerAdapterUtils);
        this.mHomeJokeFragmentViewHolder.home_joke_id_tabLayout.setupWithViewPager(this.mHomeJokeFragmentViewHolder.home_joke_id_content);
    }

    public void closeSearchPage() {
        ToastUilts.showMessage(this.mContext, "关闭搜索界面");
    }

    protected void eventBusThread(EventBusBean paramEventBusBean) {
    }

    protected int initLayout() {
        return R.layout.cy_home_joke_fragment;
    }

    protected void initLogicObject() {
    }

    protected void initPresenter() {
        this.mPresenter = new HomeJokeFragmentPresenter();
    }

    protected BaseViewBean initViewBean() {
        this.mHomeJokeFragmentViewHolder = new HomeJokeFragmentViewHolder(this.mContentView);
        return this.mHomeJokeFragmentViewHolder;
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
        initView();
        return view;
    }

    public void showSearchPage() {
        ToastUilts.showMessage(this.mContext, "打开搜索界面");
    }
}

