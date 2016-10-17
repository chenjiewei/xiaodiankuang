package chiyu.cjw.com.chiyu.ui.mainui.joke.homepage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created: chnejiewei
 * data: 2016/9/10 21:57
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public abstract class HomeJokeHomeContentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected static final int RECYCLERVIEW_BOOM = 1;
    protected static final int RECYCLERVIEW_ITEM = 0;
    protected RecyclerViewOnItemClickListener onItemClickListener;

    protected abstract void bindViewHolderMethod(RecyclerView.ViewHolder paramViewHolder, int paramInt);

    protected abstract RecyclerView.ViewHolder createViewHolderMethod(ViewGroup paramViewGroup, int paramInt);

    public int getItemCount() {
        return setRecyItemCount();
    }

    public int getItemViewType(int paramInt) {
        return setViewItemType(paramInt);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt) {
        bindViewHolderMethod(paramViewHolder, paramInt);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return createViewHolderMethod(paramViewGroup, paramInt);
    }

    public void setOnItemClickListener(RecyclerViewOnItemClickListener paramRecyclerViewOnItemClickListener) {
        this.onItemClickListener = paramRecyclerViewOnItemClickListener;
    }

    protected abstract int setRecyItemCount();

    protected abstract int setViewItemType(int paramInt);

    /**
     * 适配器每个Item点击的监听
     */
    public interface RecyclerViewOnItemClickListener {
        void onItemClick(View paramView, int paramInt);
    }
}

