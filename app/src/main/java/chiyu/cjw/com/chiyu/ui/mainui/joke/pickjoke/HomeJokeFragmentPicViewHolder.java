package chiyu.cjw.com.chiyu.ui.mainui.joke.pickjoke;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.ui.base.BaseViewBean;
import chiyu.cjw.com.chiyu.ui.base.ViewBeanGen;

/**
 * Created: chnejiewei
 * data: 2016/9/10 21:59
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokeFragmentPicViewHolder extends BaseViewBean {
    public TextView mFailetv = null;
    public RecyclerView mRecyclerView = null;
    public SwipeRefreshLayout mSwipeRefreshLayout = null;

    protected HomeJokeFragmentPicViewHolder(View paramView) {
        super(paramView);
        initView();
    }

    private void initView() {
        this.mSwipeRefreshLayout = $$(R.id.ch_home_joke_pic_sfl);
        this.mRecyclerView = $$(R.id.ch_home_joke_pic_recyclerView);
        this.mFailetv = $$(R.id.showfailetv);
    }

    protected ViewBeanGen gainViewBeanGen() {
        return null;
    }

    protected View getBackView() {
        return null;
    }
}

