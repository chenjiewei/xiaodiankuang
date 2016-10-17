package chiyu.cjw.com.chiyu.ui.mainui.joke.wordsjoke;

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
 * data: 2016/9/10 22:04
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokeFragmentWords extends BaseFragment<IHomeJokeFragmentWordsView, HomeJokeFragmentWordsPresenter> implements IHomeJokeFragmentWordsView {
    private HomeJokeWordsContentRVA mAdapterRecyclerView = null;
    private HomeJokeFragmentWordsViewHolder mHomeJokeFragmentWordsViewHolder = null;
    private SwipeRefreshLisener mSwipeRefreshLisener = null;

    private void initView() {
        this.mHomeJokeFragmentWordsViewHolder.mSwipeRefreshLayout.setColorSchemeResources(new int[]{R.color.colorPrimary});
        this.mHomeJokeFragmentWordsViewHolder.mSwipeRefreshLayout.setOnRefreshListener(this.mSwipeRefreshLisener);
        final LinearLayoutManager localLinearLayoutManager;
        localLinearLayoutManager = new LinearLayoutManager(this.mContext);
        this.mHomeJokeFragmentWordsViewHolder.mRecyclerView.setLayoutManager(localLinearLayoutManager);
        this.mAdapterRecyclerView = new HomeJokeWordsContentRVA(this.mContext);
        this.mAdapterRecyclerView.setContent(new ArrayList());
        this.mHomeJokeFragmentWordsViewHolder.mRecyclerView.setAdapter(this.mAdapterRecyclerView);
        this.mHomeJokeFragmentWordsViewHolder.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt) {
                super.onScrollStateChanged(paramAnonymousRecyclerView, paramAnonymousInt);
            }

            public void onScrolled(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt1, int paramAnonymousInt2) {
                super.onScrolled(paramAnonymousRecyclerView, paramAnonymousInt1, paramAnonymousInt2);
                if (1 + localLinearLayoutManager.findLastVisibleItemPosition() == HomeJokeFragmentWords.this.mAdapterRecyclerView.getItemCount()) {
                    if (HomeJokeFragmentWords.this.mHomeJokeFragmentWordsViewHolder.mSwipeRefreshLayout.isRefreshing()) {
                        HomeJokeFragmentWords.this.mAdapterRecyclerView.notifyItemRemoved(HomeJokeFragmentWords.this.mAdapterRecyclerView.getItemCount());
                    }
                } else {
                    return;
                }
                ((HomeJokeFragmentWordsPresenter) HomeJokeFragmentWords.this.mPresenter).loadDataContent();
            }
        });
        showSwipeRefreshLayout();
        this.mAdapterRecyclerView.setOnItemClickListener(new HomeJokeHomeContentRecyclerViewAdapter.RecyclerViewOnItemClickListener() {
            public void onItemClick(View paramAnonymousView, int paramAnonymousInt) {
                ((HomeJokeFragmentWordsPresenter) HomeJokeFragmentWords.this.mPresenter).inJokeDetail();
            }
        });
    }

    protected void eventBusThread(EventBusBean paramEventBusBean) {
    }

    public void hideLoadLayout() {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                HomeJokeFragmentWords.this.mAdapterRecyclerView.notifyItemRemoved(HomeJokeFragmentWords.this.mAdapterRecyclerView.getItemCount());
            }
        });
    }

    public void hideSwipeRefreshLayout() {
        if (!this.mHomeJokeFragmentWordsViewHolder.mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        this.mHomeJokeFragmentWordsViewHolder.mSwipeRefreshLayout.post(new Runnable() {
            public void run() {
                HomeJokeFragmentWords.this.mHomeJokeFragmentWordsViewHolder.mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    protected int initLayout() {
        return R.layout.cy_home_joke_words_fragent;
    }

    protected void initLogicObject() {
        this.mSwipeRefreshLisener = new SwipeRefreshLisener();
    }

    protected void initPresenter() {
        this.mPresenter = new HomeJokeFragmentWordsPresenter(this.mContext);
    }

    protected BaseViewBean initViewBean() {
        this.mHomeJokeFragmentWordsViewHolder = new HomeJokeFragmentWordsViewHolder(this.mContentView);
        return this.mHomeJokeFragmentWordsViewHolder;
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
                HomeJokeFragmentWords.this.mHomeJokeFragmentWordsViewHolder.mRecyclerView.setVisibility(View.VISIBLE);
                HomeJokeFragmentWords.this.mHomeJokeFragmentWordsViewHolder.mFailetv.setVisibility(View.INVISIBLE);
                HomeJokeFragmentWords.this.mAdapterRecyclerView.setContent(paramArrayList);
                HomeJokeFragmentWords.this.mAdapterRecyclerView.notifyDataSetChanged();
            }
        });
    }

    public void showRefreshFaile(final String paramString) {
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                HomeJokeFragmentWords.this.mHomeJokeFragmentWordsViewHolder.mRecyclerView.setVisibility(View.GONE);
                HomeJokeFragmentWords.this.mHomeJokeFragmentWordsViewHolder.mFailetv.setVisibility(View.VISIBLE);
                HomeJokeFragmentWords.this.mHomeJokeFragmentWordsViewHolder.mFailetv.setText(paramString);
            }
        });
    }

    public void showSwipeRefreshLayout() {
        if (this.mHomeJokeFragmentWordsViewHolder.mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        this.mHomeJokeFragmentWordsViewHolder.mSwipeRefreshLayout.post(new Runnable() {
            public void run() {
                HomeJokeFragmentWords.this.mHomeJokeFragmentWordsViewHolder.mSwipeRefreshLayout.setRefreshing(true);
                HomeJokeFragmentWords.this.mSwipeRefreshLisener.onRefresh();
            }
        });
    }

    private class SwipeRefreshLisener
            implements SwipeRefreshLayout.OnRefreshListener {
        private SwipeRefreshLisener() {
        }

        public void onRefresh() {
            mPresenter.refreshDataContent();
        }
    }
}

