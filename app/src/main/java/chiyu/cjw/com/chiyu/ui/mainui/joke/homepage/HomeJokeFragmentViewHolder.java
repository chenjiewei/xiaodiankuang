package chiyu.cjw.com.chiyu.ui.mainui.joke.homepage;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.ui.base.BaseViewBean;
import chiyu.cjw.com.chiyu.ui.base.ViewBeanGen;

/**
 * Created: chnejiewei
 * data: 2016/9/10 21:56
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokeFragmentViewHolder extends BaseViewBean {
    public AppBarLayout home_joke_id_appBarLayout = null;
    public ViewPager home_joke_id_content = null;
    public CoordinatorLayout home_joke_id_coordinatorLayout = null;
    public TabLayout home_joke_id_tabLayout = null;
    public Toolbar home_joke_id_toolbar = null;

    protected HomeJokeFragmentViewHolder(View paramView) {
        super(paramView);
        initView();
    }

    private void initView() {
        this.home_joke_id_coordinatorLayout = ((CoordinatorLayout) $$(R.id.home_joke_id_coordinatorLayout));
        this.home_joke_id_appBarLayout = ((AppBarLayout) $$(R.id.home_joke_id_appBarLayout));
        this.home_joke_id_toolbar = ((Toolbar) $$(R.id.home_joke_id_toolbar));
        this.home_joke_id_tabLayout = ((TabLayout) $$(R.id.home_joke_id_tabLayout));
        this.home_joke_id_content = ((ViewPager) $$(R.id.home_joke_id_content));
    }

    protected ViewBeanGen gainViewBeanGen() {
        return null;
    }

    protected View getBackView() {
        return null;
    }
}

