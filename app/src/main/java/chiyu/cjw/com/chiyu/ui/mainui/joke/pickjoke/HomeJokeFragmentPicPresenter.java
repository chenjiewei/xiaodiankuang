package chiyu.cjw.com.chiyu.ui.mainui.joke.pickjoke;

import android.content.Context;
import android.os.Handler;

import com.cjw.chiyu.common.utils.CheckNet;

import java.util.ArrayList;

import chiyu.cjw.com.chiyu.manager.data.joke.BaseJokeHandler;
import chiyu.cjw.com.chiyu.manager.data.joke.JokePicAndGif;
import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;
import chiyu.cjw.com.chiyu.ui.base.BasePresenter;
import chiyu.cjw.com.chiyu.ui.mainui.joke.JokeDataChangeListener;
import chiyu.cjw.com.chiyu.ui.uiutils.ToastUilts;

/**
 * Created: chnejiewei
 * data: 2016/9/10 21:58
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokeFragmentPicPresenter extends BasePresenter<IHomeJokeFragmentPickView> implements IHomeJokeFragmentPicPresenter {
    private Handler handler = new Handler();
    private boolean isHadLoadingData = false;
    private boolean isLoadingData = false;
    private BaseJokeHandler mBaseJokeHandler = null;
    private Context mContext;
    public JokeDataChangeListener mJokePicDataChangeListenerimpl = new JokePicDataChangeListenerimpl();

    public HomeJokeFragmentPicPresenter(Context paramContext) {
        this.mContext = paramContext;
        this.mBaseJokeHandler = JokePicAndGif.newInstances();
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
                if (CheckNet.isNetworkConnected(HomeJokeFragmentPicPresenter.this.mContext)) {
                    HomeJokeFragmentPicPresenter.this.mBaseJokeHandler.getData(BaseJokeHandler.ObtainDataType.LOAD, mJokePicDataChangeListenerimpl);
                    return;
                }
                mJokePicDataChangeListenerimpl.loadJokeFaile("当前无有用网络");
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
                if (CheckNet.isNetworkConnected(mContext)) {
                    mBaseJokeHandler.getData(BaseJokeHandler.ObtainDataType.REFRESH, mJokePicDataChangeListenerimpl);
                    return;
                }
                mJokePicDataChangeListenerimpl.refreshJokeFaile("当前无有用网络");
            }
        }, 1000L);
    }

    public void shareContent() {
    }

    private class JokePicDataChangeListenerimpl implements JokeDataChangeListener {
        private JokePicDataChangeListenerimpl() {
        }

        @Override
        public void loadJoke(ArrayList<HomeUIJokeModel> paramArrayList) {
            ((IHomeJokeFragmentPickView) HomeJokeFragmentPicPresenter.this.mView).paddingContentToView(paramArrayList);
            ((IHomeJokeFragmentPickView) HomeJokeFragmentPicPresenter.this.mView).hideLoadLayout();
            isLoadingData = false;
        }

        @Override
        public void loadJokeFaile(String paramString) {
            mView.hideLoadLayout();
            ToastUilts.showMessage(mContext, paramString);
            isLoadingData = false;
        }

        @Override
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
                ToastUilts.showMessage(HomeJokeFragmentPicPresenter.this.mContext, paramString);
            } else {
                mView.showRefreshFaile(paramString);
            }
        }
    }
}

