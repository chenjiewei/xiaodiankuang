package chiyu.cjw.com.chiyu.ui.mainui.joke.randomjoke;

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
 * data: 2016/9/10 22:00
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokeFragmentRandom extends BaseFragment<IHomeJokeFragmentRandomView, HomeJokeFragmentRandomPresenter> implements IHomeJokeFragmentRandomView {
    private HomeJokeRandomContentRVA mAdapterRecyclerView = null;
    private HomeJokeFragmentRandomViewHolder mHomeJokeFragmentRandomViewHolder = null;
    private SwipeRefreshLisener mSwipeRefreshLisener = null;

    private void initView() {
        this.mHomeJokeFragmentRandomViewHolder.mSwipeRefreshLayout.setColorSchemeResources(new int[]{R.color.colorPrimary});
        this.mHomeJokeFragmentRandomViewHolder.mSwipeRefreshLayout.setOnRefreshListener(this.mSwipeRefreshLisener);
        final LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(this.mContext);
        this.mHomeJokeFragmentRandomViewHolder.mRecyclerView.setLayoutManager(localLinearLayoutManager);
        this.mAdapterRecyclerView = new HomeJokeRandomContentRVA(this.mContext);
        this.mAdapterRecyclerView.setContent(new ArrayList());
        this.mHomeJokeFragmentRandomViewHolder.mRecyclerView.setAdapter(this.mAdapterRecyclerView);
        this.mHomeJokeFragmentRandomViewHolder.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt) {
                super.onScrollStateChanged(paramAnonymousRecyclerView, paramAnonymousInt);
            }

            public void onScrolled(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt1, int paramAnonymousInt2) {
                super.onScrolled(paramAnonymousRecyclerView, paramAnonymousInt1, paramAnonymousInt2);
                if (1 + localLinearLayoutManager.findLastVisibleItemPosition() == HomeJokeFragmentRandom.this.mAdapterRecyclerView.getItemCount()) {
                    if (mHomeJokeFragmentRandomViewHolder.mSwipeRefreshLayout.isRefreshing()) {
                        mAdapterRecyclerView.notifyItemRemoved(HomeJokeFragmentRandom.this.mAdapterRecyclerView.getItemCount());
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
               mPresenter.inJokeDetail();
            }
        });
    }

    protected void eventBusThread(EventBusBean paramEventBusBean) {
    }

    public void hideLoadLayout() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
              mAdapterRecyclerView.notifyItemRemoved(mAdapterRecyclerView.getItemCount());
            }
        });
    }

    public void hideSwipeRefreshLayout() {
        if (!this.mHomeJokeFragmentRandomViewHolder.mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        this.mHomeJokeFragmentRandomViewHolder.mSwipeRefreshLayout.post(new Runnable() {
            public void run() {
                HomeJokeFragmentRandom.this.mHomeJokeFragmentRandomViewHolder.mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    protected int initLayout() {
        return R.layout.cy_home_joke_random_fragment;
    }

    protected void initLogicObject() {
        this.mSwipeRefreshLisener = new SwipeRefreshLisener();
    }

    protected void initPresenter() {
        this.mPresenter = new HomeJokeFragmentRandomPresenter(this.mContext);
    }

    protected BaseViewBean initViewBean() {
        this.mHomeJokeFragmentRandomViewHolder = new HomeJokeFragmentRandomViewHolder(this.mContentView);
        return this.mHomeJokeFragmentRandomViewHolder;
    }

    public void jumpDetailPage() {
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        super.onCreateView(paramLayoutInflater, paramViewGroup, paramBundle);
        initView();
        return this.mContentView;
    }

    public void paddingContentToView(final ArrayList<HomeUIJokeModel> paramArrayList) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                HomeJokeFragmentRandom.this.mHomeJokeFragmentRandomViewHolder.mRecyclerView.setVisibility(View.VISIBLE);
                HomeJokeFragmentRandom.this.mHomeJokeFragmentRandomViewHolder.mFailetv.setVisibility(View.INVISIBLE);
                HomeJokeFragmentRandom.this.mAdapterRecyclerView.setContent(paramArrayList);
                HomeJokeFragmentRandom.this.mAdapterRecyclerView.notifyDataSetChanged();
            }
        });
    }

    public void showRefreshFaile(final String paramString) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                HomeJokeFragmentRandom.this.mHomeJokeFragmentRandomViewHolder.mRecyclerView.setVisibility(View.GONE);
                HomeJokeFragmentRandom.this.mHomeJokeFragmentRandomViewHolder.mFailetv.setVisibility(View.VISIBLE);
                HomeJokeFragmentRandom.this.mHomeJokeFragmentRandomViewHolder.mFailetv.setText(paramString);
            }
        });
    }

    public void showSwipeRefreshLayout() {
        if (this.mHomeJokeFragmentRandomViewHolder.mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        this.mHomeJokeFragmentRandomViewHolder.mSwipeRefreshLayout.post(new Runnable() {
            public void run() {
                HomeJokeFragmentRandom.this.mHomeJokeFragmentRandomViewHolder.mSwipeRefreshLayout.setRefreshing(true);
                HomeJokeFragmentRandom.this.mSwipeRefreshLisener.onRefresh();
            }
        });
    }

    private class SwipeRefreshLisener implements SwipeRefreshLayout.OnRefreshListener {
        private SwipeRefreshLisener() {
        }

        public void onRefresh() {
            mPresenter.refreshDataContent();
        }
    }
}

