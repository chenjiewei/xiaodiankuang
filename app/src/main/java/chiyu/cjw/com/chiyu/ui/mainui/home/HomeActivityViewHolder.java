package chiyu.cjw.com.chiyu.ui.mainui.home;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.TextView;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.ui.base.BaseViewBean;
import chiyu.cjw.com.chiyu.ui.base.ViewBeanGen;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:49
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeActivityViewHolder extends BaseViewBean {
    public TextView home_id_userinfo_tv;
    public TextView home_id_username_tv;
    public CircleImageView home_id_userportrait_ci;
    public NavigationView home_navigation_view;
    public DrawerLayout mDrawerLayout;
    private View.OnClickListener mListener = null;

    public HomeActivityViewHolder(View paramView, View.OnClickListener paramOnClickListener) {
        super(paramView);
        this.mListener = paramOnClickListener;
        initView();
        initViewListener();
    }

    private void initView() {
        this.home_navigation_view = ((NavigationView) $$(R.id.home_navigation_view));
        this.mDrawerLayout = ((DrawerLayout) $$(R.id.id_drawer_layout));
        View view = this.home_navigation_view.getHeaderView(0);
        View viewRoot = this.rootView;
        this.rootView = view;
        this.home_id_userinfo_tv = (TextView) $$(R.id.home_id_userinfo_tv);
        this.home_id_username_tv = (TextView) $$(R.id.home_id_username_tv);
        this.home_id_userportrait_ci = (CircleImageView) $$(R.id.home_id_userportrait_ci);
        this.rootView = viewRoot;
    }

    private void initViewListener() {
        this.home_id_userportrait_ci.setOnClickListener(this.mListener);
    }

    public ViewBeanGen gainViewBeanGen() {
        return new ViewBeanGen(this);
    }

    public View getBackView() {
        return null;
    }
}
