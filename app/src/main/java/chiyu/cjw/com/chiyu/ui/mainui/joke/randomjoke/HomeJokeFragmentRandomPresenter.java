package chiyu.cjw.com.chiyu.ui.mainui.joke.randomjoke;


import android.content.Context;
import android.os.Handler;

import com.cjw.chiyu.common.utils.CheckNet;

import java.util.ArrayList;

import chiyu.cjw.com.chiyu.manager.data.joke.BaseJokeHandler;
import chiyu.cjw.com.chiyu.manager.data.joke.JokeRandom;
import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;
import chiyu.cjw.com.chiyu.ui.base.BasePresenter;
import chiyu.cjw.com.chiyu.ui.mainui.joke.JokeDataChangeListener;
import chiyu.cjw.com.chiyu.ui.uiutils.ToastUilts;

/**
 * Created: chnejiewei
 * data: 2016/9/10 22:01
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokeFragmentRandomPresenter extends BasePresenter<IHomeJokeFragmentRandomView> implements IHomeJokeFragmentRandomPresenter {
    private Handler handler = new Handler();
    private boolean isHadLoadingData = false;
    private boolean isLoadingData = false;
    private BaseJokeHandler mBaseJokeHandler = null;
    private Context mContext;
    public JokeDataChangeListener mJokeRandomDataChangeListenerimpl = new JokeRandomDataChangeListenerimpl();

    public HomeJokeFragmentRandomPresenter(Context paramContext) {
        this.mContext = paramContext;
        this.mBaseJokeHandler = JokeRandom.newInstances();
    }

    public void inJokeDetail() {
        ToastUilts.showMessage(this.mContext, "跳转到详细界面");
    }

    public void loadDataContent() {
        if (this.isLoadingData) {
            return;
        }
        this.isLoadingData = true;
        this.handler.postDelayed(new Runnable() {
            public void run() {
                if (CheckNet.isNetworkConnected(HomeJokeFragmentRandomPresenter.this.mContext)) {
                    HomeJokeFragmentRandomPresenter.this.mBaseJokeHandler.getData(BaseJokeHandler.ObtainDataType.LOAD, HomeJokeFragmentRandomPresenter.this.mJokeRandomDataChangeListenerimpl);
                    return;
                }
                HomeJokeFragmentRandomPresenter.this.mJokeRandomDataChangeListenerimpl.loadJokeFaile("当前无有用网络");
            }
        }, 1000L);
    }

    public void pressAgainst() {
    }

    public void pressAgree() {
    }

    public void refreshDataContent() {
        if (this.isLoadingData) {
            return;
        }
        this.isLoadingData = true;
        this.handler.postDelayed(new Runnable() {
            public void run() {
                if (CheckNet.isNetworkConnected(HomeJokeFragmentRandomPresenter.this.mContext)) {
                    HomeJokeFragmentRandomPresenter.this.mBaseJokeHandler.getData(BaseJokeHandler.ObtainDataType.REFRESH, HomeJokeFragmentRandomPresenter.this.mJokeRandomDataChangeListenerimpl);
                    return;
                }
                HomeJokeFragmentRandomPresenter.this.mJokeRandomDataChangeListenerimpl.refreshJokeFaile("当前无有用网络");
            }
        }, 1000L);
    }

    public void shareContent() {
    }

    private class JokeRandomDataChangeListenerimpl implements JokeDataChangeListener {
        public void loadJoke(ArrayList<HomeUIJokeModel> paramArrayList) {
            mView.paddingContentToView(paramArrayList);
            mView.hideLoadLayout();
            isLoadingData = false;
        }

        public void loadJokeFaile(String paramString) {
            mView.hideLoadLayout();
            ToastUilts.showMessage(HomeJokeFragmentRandomPresenter.this.mContext, paramString);
            isLoadingData = false;
        }

        public void refreshJoke(ArrayList<HomeUIJokeModel> paramArrayList) {
            mView.paddingContentToView(paramArrayList);
            mView.hideSwipeRefreshLayout();
            isLoadingData = false;
            isHadLoadingData = true;
        }

        public void refreshJokeFaile(String paramString) {
            mView.hideSwipeRefreshLayout();
            isLoadingData = false;
            if (isHadLoadingData) {
                ToastUilts.showMessage(mContext, paramString);
            } else {
                mView.showRefreshFaile(paramString);
            }
        }
    }
}

