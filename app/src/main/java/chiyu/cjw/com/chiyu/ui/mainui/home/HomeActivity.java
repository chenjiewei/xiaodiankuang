package chiyu.cjw.com.chiyu.ui.mainui.home;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.cjw.chiyu.common.log.LogPoxy;
import com.cjw.chiyu.common.persistentdata.sharepreferencesutils.SpManager;

import java.lang.ref.WeakReference;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.ui.base.BaseActivity;
import chiyu.cjw.com.chiyu.ui.base.BaseFragment;
import chiyu.cjw.com.chiyu.ui.base.BaseViewBean;
import chiyu.cjw.com.chiyu.ui.eventbus.EventBusBean;
import chiyu.cjw.com.chiyu.ui.eventbus.EventBusConstant;
import chiyu.cjw.com.chiyu.ui.mainui.ghoststory.HomeGhostStoryFragment;
import chiyu.cjw.com.chiyu.ui.mainui.interestingtools.HomeInterestingFragment;
import chiyu.cjw.com.chiyu.ui.mainui.joke.homepage.HomeJokeFragment;
import chiyu.cjw.com.chiyu.ui.mainui.myattention.HomeMyAttentionFragment;
import chiyu.cjw.com.chiyu.ui.mainui.mycollection.HomeMyCollectionFragment;
import chiyu.cjw.com.chiyu.ui.mainui.myhistory.HomeMyHistoryFragment;
import chiyu.cjw.com.chiyu.ui.mainui.setting.HomeSettingFragment;
import chiyu.cjw.com.chiyu.ui.uiconfig.UiConfigConstant;
import chiyu.cjw.com.chiyu.ui.uiutils.FragmentUtils;
import chiyu.cjw.com.chiyu.ui.uiutils.ToastUilts;
import chiyu.cjw.com.chiyu.ui.uiutils.UiThreadPoolUtils;
import chiyu.cjw.com.chiyu.ui.welcomeui.introui.IntroUi;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:49
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeActivity extends BaseActivity<IHomeActivityView, HomeAvtivityPresenter> implements IHomeActivityView, View.OnClickListener {
    private static final String TAG = "HomeActivity";
    public static final long MAX_DOUBLE_BACK_DURATION = 1500L;
    private long lastBackKeyDownTick = 0L;
    private Fragment mCurrentFragment;
    private FragmentManager mFragmentManager;
    private FragmentUtils mFragmentUtils;
    private HomeActivityViewHolder mHomeActivityViewHolder = null;
    private MenuItem mPreMenuItem;

    private void disableNavigationViewScrollbars(NavigationView paramNavigationView) {
        if (paramNavigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) paramNavigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    private void initViewDispaly() {
        if ((this.mHomeActivityViewHolder != null) && (this.mHomeActivityViewHolder.home_navigation_view != null)) {
            disableNavigationViewScrollbars(this.mHomeActivityViewHolder.home_navigation_view);
        }
        this.mCurrentFragment = this.mFragmentUtils.createFragment(HomeJokeFragment.class);
        this.mFragmentManager.beginTransaction().add(R.id.frame_content, this.mCurrentFragment).commit();
        this.mPreMenuItem = this.mHomeActivityViewHolder.home_navigation_view.getMenu().getItem(0);
        this.mPreMenuItem.setChecked(true);
        ColorStateList colorStateList = getBaseContext().getResources().getColorStateList(R.color.md_orange_200);
        this.mHomeActivityViewHolder.home_navigation_view.setItemTextColor(colorStateList);
        this.mHomeActivityViewHolder.home_navigation_view.setItemIconTintList(colorStateList);
    }

    private void setNavigationViewItemClickListener() {
        this.mHomeActivityViewHolder.home_navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (mPreMenuItem != null) {
                    mPreMenuItem.setChecked(false);
                }
                switch (menuItem.getItemId()) {
                    case R.id.nav_joke:
                        switchFragment(HomeJokeFragment.class);
                        break;
                    case R.id.nav_ghost:
                        switchFragment(HomeGhostStoryFragment.class);
                        break;
                    case R.id.nav_interesting:
                        switchFragment(HomeInterestingFragment.class);
                        break;
                    case R.id.nav_me_history:
                        switchFragment(HomeMyHistoryFragment.class);
                        break;
                    case R.id.nav_me_house:
                        switchFragment(HomeMyCollectionFragment.class);
                        break;
                    case R.id.nav_me_attention:
                        switchFragment(HomeMyAttentionFragment.class);
                        break;
                    case R.id.nav_me_setting:
                        switchFragment(HomeSettingFragment.class);
                        break;
                    default:
                        break;
                }
                menuItem.setChecked(true);
                mHomeActivityViewHolder.mDrawerLayout.closeDrawer(Gravity.LEFT);
                mPreMenuItem = menuItem;
                return false;
            }
        });
    }

    private void switchFragment(Class<?> paramClass) {
        BaseFragment fragment = this.mFragmentUtils.createFragment(paramClass);
        if (fragment.isAdded()) {
            LogPoxy.i(TAG, "home page add fragment");
            this.mFragmentManager.beginTransaction().hide(this.mCurrentFragment).show(fragment).commitAllowingStateLoss();
        } else {
            LogPoxy.i(TAG, "home page not add fragment");
            this.mFragmentManager.beginTransaction().hide(this.mCurrentFragment).add(R.id.frame_content, fragment).commitAllowingStateLoss();
        }
        mCurrentFragment = fragment;
    }

    @Override
    protected void eventBusThread(EventBusBean paramEventBusBean) {
        if (EventBusConstant.EVENT_BUS_NAVIGATION_VIEW_SWITCH_OPEN.equals(paramEventBusBean.getMsg())) {
            if (!this.mHomeActivityViewHolder.mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                this.mHomeActivityViewHolder.mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        } else if (EventBusConstant.EVENT_BUS_CLOSE_ACTIVITY.equals(paramEventBusBean.getMsg())) {
            finish();
        } else if (EventBusConstant.EVENT_BUS_NAVIGATION_VIEW_SWITCH_CLOSE.equals(paramEventBusBean.getMsg())) {
            if (this.mHomeActivityViewHolder.mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                this.mHomeActivityViewHolder.mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        }
    }

    protected void initLogicObject() {
        this.mFragmentManager = getSupportFragmentManager();
        this.mFragmentUtils = new FragmentUtils();
    }

    protected void initPresenter() {
        this.mPresenter = new HomeAvtivityPresenter(this);
    }

    protected BaseViewBean initViewBean() {
        this.mHomeActivityViewHolder = new HomeActivityViewHolder(getWindow().getDecorView(), this);
        return this.mHomeActivityViewHolder;
    }

    protected void inittoolBar() {
    }

    public void onBackPressed() {
        if (this.mHomeActivityViewHolder.mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
            this.mHomeActivityViewHolder.mDrawerLayout.closeDrawer(Gravity.LEFT);
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
            ToastUilts.showMessage(this, UiConfigConstant.EXIT_APP);
            this.lastBackKeyDownTick = currentTimeMillis;
            return;
        }
        finish();
        System.exit(0);
    }

    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.home_navigation_view:
                ((HomeAvtivityPresenter) this.mPresenter).validateCredentials();
                return;
            default:
                return;
        }

    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        initViewDispaly();
        setNavigationViewItemClickListener();
        ((HomeAvtivityPresenter) this.mPresenter).updateUserInfo();
        if (!SpManager.getNewInstance().gainBooleanValue("IS_NEVER_ROOT")) {
            UiThreadPoolUtils.newInstances().executeRunTask(new StartIntroPageRunnable(this));
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected int setLayoutResouceID() {
        return R.layout.cy_home_activity_layout;
    }

    @Override
    public void showUserInfo(String paramString1, String paramString2, Bitmap paramBitmap) {
        this.mHomeActivityViewHolder.home_id_userinfo_tv.setText(paramString1);
        this.mHomeActivityViewHolder.home_id_userinfo_tv.setText(paramString2);
        this.mHomeActivityViewHolder.home_id_userportrait_ci.setImageBitmap(paramBitmap);
    }

    @Override
    public void toLoginActivity() {
    }

    @Override
    public void toUserActivity() {
    }

    private class StartIntroPageRunnable implements Runnable {
        private WeakReference<BaseActivity> weakReference = null;

        public StartIntroPageRunnable(BaseActivity paramBaseActivity) {
            this.weakReference = new WeakReference(paramBaseActivity);
        }

        public void run() {
            if ((this.weakReference == null) || (this.weakReference.get() == null)) {
                return;
            }
            ((BaseActivity) this.weakReference.get()).startActivityWithOutExtras(IntroUi.class);
        }
    }
}
