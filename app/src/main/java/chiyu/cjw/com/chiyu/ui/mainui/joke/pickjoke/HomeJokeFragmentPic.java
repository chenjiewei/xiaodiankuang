package chiyu.cjw.com.chiyu.ui.mainui.joke.pickjoke;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;
import chiyu.cjw.com.chiyu.ui.base.BaseFragment;
import chiyu.cjw.com.chiyu.ui.base.BaseViewBean;
import chiyu.cjw.com.chiyu.ui.eventbus.EventBusBean;
import chiyu.cjw.com.chiyu.ui.mainui.joke.homepage.HomeJokeHomeContentRecyclerViewAdapter;

/**
 * Created: chnejiewei
 * data: 2016/9/10 21:58
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokeFragmentPic extends BaseFragment<IHomeJokeFragmentPickView, HomeJokeFragmentPicPresenter> implements IHomeJokeFragmentPickView {
    private HomeJokePicContentRVA mAdapterRecyclerView = null;
    private HomeJokeFragmentPicViewHolder mHomeJokeFragmentPicViewHolder = null;
    private SwipeRefreshLisener mSwipeRefreshLisener = null;

    private void initView() {
        this.mHomeJokeFragmentPicViewHolder.mSwipeRefreshLayout.setColorSchemeResources(new int[]{R.color.colorPrimary});
        this.mHomeJokeFragmentPicViewHolder.mSwipeRefreshLayout.setOnRefreshListener(this.mSwipeRefreshLisener);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext);
        this.mHomeJokeFragmentPicViewHolder.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mAdapterRecyclerView = new HomeJokePicContentRVA(this.mContext);
        this.mAdapterRecyclerView.setContent(new ArrayList());
        this.mHomeJokeFragmentPicViewHolder.mRecyclerView.setAdapter(this.mAdapterRecyclerView);
        this.mHomeJokeFragmentPicViewHolder.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt) {
                super.onScrollStateChanged(paramAnonymousRecyclerView, paramAnonymousInt);
            }

            public void onScrolled(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt1, int paramAnonymousInt2) {
                super.onScrolled(paramAnonymousRecyclerView, paramAnonymousInt1, paramAnonymousInt2);
                if (1 + linearLayoutManager.findLastVisibleItemPosition() == HomeJokeFragmentPic.this.mAdapterRecyclerView.getItemCount()) {
                    if (HomeJokeFragmentPic.this.mHomeJokeFragmentPicViewHolder.mSwipeRefreshLayout.isRefreshing()) {
                        HomeJokeFragmentPic.this.mAdapterRecyclerView.notifyItemRemoved(HomeJokeFragmentPic.this.mAdapterRecyclerView.getItemCount());
                    }
                } else {
                    return;
                }
                mPresenter.loadDataContent();
            }
        });
        showSwipeRefreshLayout();
        this.mAdapterRecyclerView.setOnItemClickListener(new HomeJokeHomeContentRecyclerViewAdapter.RecyclerViewOnItemClickListener() {
            public void onItemClick(View paramAnonymousView, int paramAnonymousInt) {
                ((HomeJokeFragmentPicPresenter) HomeJokeFragmentPic.this.mPresenter).inJokeDetail();
            }
        });
    }

    protected void eventBusThread(EventBusBean paramEventBusBean) {
    }

    @Override
    public void hideLoadLayout() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                HomeJokeFragmentPic.this.mAdapterRecyclerView.notifyItemRemoved(HomeJokeFragmentPic.this.mAdapterRecyclerView.getItemCount());
            }
        });
    }

    @Override
    public void hideSwipeRefreshLayout() {
        if (!this.mHomeJokeFragmentPicViewHolder.mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        this.mHomeJokeFragmentPicViewHolder.mSwipeRefreshLayout.post(new Runnable() {
            public void run() {
                HomeJokeFragmentPic.this.mHomeJokeFragmentPicViewHolder.mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    protected int initLayout() {
        return R.layout.cy_home_joke_pic_fragment;
    }

    protected void initLogicObject() {
        this.mSwipeRefreshLisener = new SwipeRefreshLisener();
    }

    protected void initPresenter() {
        this.mPresenter = new HomeJokeFragmentPicPresenter(this.mContext);
    }

    protected BaseViewBean initViewBean() {
        this.mHomeJokeFragmentPicViewHolder = new HomeJokeFragmentPicViewHolder(this.mContentView);
        return this.mHomeJokeFragmentPicViewHolder;
    }

    public void jumpDetailPage() {
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
        initView();
        return this.mContentView;
    }

    @Override
    public void paddingContentToView(final ArrayList<HomeUIJokeModel> paramArrayList) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                HomeJokeFragmentPic.this.mHomeJokeFragmentPicViewHolder.mRecyclerView.setVisibility(View.VISIBLE);
                HomeJokeFragmentPic.this.mHomeJokeFragmentPicViewHolder.mFailetv.setVisibility(View.INVISIBLE);
                HomeJokeFragmentPic.this.mAdapterRecyclerView.setContent(paramArrayList);
                HomeJokeFragmentPic.this.mAdapterRecyclerView.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showRefreshFaile(final String paramString) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                HomeJokeFragmentPic.this.mHomeJokeFragmentPicViewHolder.mRecyclerView.setVisibility(View.GONE);
                HomeJokeFragmentPic.this.mHomeJokeFragmentPicViewHolder.mFailetv.setVisibility(View.VISIBLE);
                HomeJokeFragmentPic.this.mHomeJokeFragmentPicViewHolder.mFailetv.setText(paramString);
            }
        });
    }

    private void showSwipeRefreshLayout() {
        if (this.mHomeJokeFragmentPicViewHolder.mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        this.mHomeJokeFragmentPicViewHolder.mSwipeRefreshLayout.post(new Runnable() {
            public void run() {
                HomeJokeFragmentPic.this.mHomeJokeFragmentPicViewHolder.mSwipeRefreshLayout.setRefreshing(true);
                HomeJokeFragmentPic.this.mSwipeRefreshLisener.onRefresh();
            }
        });
    }

    private class SwipeRefreshLisener implements SwipeRefreshLayout.OnRefreshListener {
        public void onRefresh() {
            mPresenter.refreshDataContent();
        }
    }
}

