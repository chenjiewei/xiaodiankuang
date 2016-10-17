package chiyu.cjw.com.chiyu.ui.mainui.joke.wordsjoke;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjw.chiyu.common.utils.RuningExceptionUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;
import chiyu.cjw.com.chiyu.ui.mainui.joke.homepage.HomeJokeHomeContentRecyclerViewAdapter;

/**
 * Created: chnejiewei
 * data: 2016/9/10 22:05
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokeWordsContentRVA extends HomeJokeHomeContentRecyclerViewAdapter {
    private List<HomeUIJokeModel> dataContent_list = null;
    private Context mContext;

    public HomeJokeWordsContentRVA(Context paramContext) {
        this.mContext = paramContext.getApplicationContext();
    }

    private void checkData() {
        if (this.dataContent_list == null) {
            RuningExceptionUtils.throwsRunException("HomeJokeWordsContentRVA == null");
        }
    }

    protected void bindViewHolderMethod(final RecyclerView.ViewHolder paramViewHolder, int paramInt) {
        checkData();
        if (paramViewHolder instanceof NomalItemViewHolder) {
            HomeUIJokeModel localHomeUIJokeModel = (HomeUIJokeModel) this.dataContent_list.get(paramInt);
            if (this.onItemClickListener != null) {
                paramViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                        int i = paramViewHolder.getLayoutPosition();
                        HomeJokeWordsContentRVA.this.onItemClickListener.onItemClick(paramViewHolder.itemView, i);
                    }
                });
            }
            ((NomalItemViewHolder) paramViewHolder).joke_content.setText(Html.fromHtml(localHomeUIJokeModel.content));
            ((NomalItemViewHolder) paramViewHolder).user_nick.setText(localHomeUIJokeModel.userNick);
            ((NomalItemViewHolder) paramViewHolder).cell_tv_one.setText(String.valueOf(localHomeUIJokeModel.aggreeNum));
            ((NomalItemViewHolder) paramViewHolder).cell_tv_two.setText(String.valueOf(localHomeUIJokeModel.againstNum));
            ((NomalItemViewHolder) paramViewHolder).cell_tv_three.setText(String.valueOf(localHomeUIJokeModel.commentNum));
            ((NomalItemViewHolder) paramViewHolder).cell_tv_four.setText(String.valueOf(localHomeUIJokeModel.shareNum));
            ((NomalItemViewHolder) paramViewHolder).user_origal_tv.setText(localHomeUIJokeModel.origialPlace);
            if ("error".equals(localHomeUIJokeModel.userHeadPic)) {
                Picasso.with(this.mContext).load(R.mipmap.cy_home_headimage).into(((NomalItemViewHolder) paramViewHolder).home_joke_words_id_userheadpic_small);
            }
        }
    }

    protected RecyclerView.ViewHolder createViewHolderMethod(ViewGroup paramViewGroup, int paramInt) {
        RecyclerView.ViewHolder localNomalItemViewHolder;
        if (RECYCLERVIEW_BOOM == paramInt) {
            localNomalItemViewHolder = new BottomItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.cy_home_joke_words_recyitem_bottom, paramViewGroup, false));
        } else {
            localNomalItemViewHolder = new NomalItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.cy_home_joke_words_recyitem_nomal, paramViewGroup, false));
        }
        return localNomalItemViewHolder;
    }

    public void setContent(ArrayList<HomeUIJokeModel> paramArrayList) {
        this.dataContent_list = null;
        this.dataContent_list = paramArrayList;
    }

    protected int setRecyItemCount() {
        checkData();
        if (this.dataContent_list.size() == 0) {
            return 0;
        }
        return 1 + this.dataContent_list.size();
    }

    protected int setViewItemType(int paramInt) {
        checkData();
        if (paramInt + 1 == getItemCount()) {
            return RECYCLERVIEW_BOOM;
        }
        return RECYCLERVIEW_ITEM;
    }

    private class BottomItemViewHolder
            extends RecyclerView.ViewHolder {
        public BottomItemViewHolder(View paramView) {
            super(paramView);
        }
    }

    private class NomalItemViewHolder
            extends RecyclerView.ViewHolder {
        public TextView cell_tv_four;
        public TextView cell_tv_one;
        public TextView cell_tv_three;
        public TextView cell_tv_two;
        public LinearLayout home_joke_words_against_layout;
        public LinearLayout home_joke_words_aggree_layout;
        public LinearLayout home_joke_words_comment_layout;
        public ImageView home_joke_words_id_userheadpic_small;
        public LinearLayout home_joke_words_share_layout;
        public TextView joke_content;
        public TextView user_nick;
        public TextView user_origal_tv;

        public NomalItemViewHolder(View paramView) {
            super(paramView);
            this.home_joke_words_id_userheadpic_small = ((ImageView) paramView.findViewById(R.id.home_joke_words_id_userheadpic_small));
            this.user_nick = ((TextView) paramView.findViewById(R.id.user_nick));
            this.joke_content = ((TextView) paramView.findViewById(R.id.joke_content));
            this.user_origal_tv = ((TextView) paramView.findViewById(R.id.user_origal_tv));
            this.cell_tv_one = ((TextView) paramView.findViewById(R.id.cell_tv_one));
            this.cell_tv_two = ((TextView) paramView.findViewById(R.id.cell_tv_two));
            this.cell_tv_three = ((TextView) paramView.findViewById(R.id.cell_tv_three));
            this.cell_tv_four = ((TextView) paramView.findViewById(R.id.cell_tv_four));
            this.home_joke_words_aggree_layout = ((LinearLayout) paramView.findViewById(R.id.home_joke_words_aggree_layout));
            this.home_joke_words_against_layout = ((LinearLayout) paramView.findViewById(R.id.home_joke_words_against_layout));
            this.home_joke_words_comment_layout = ((LinearLayout) paramView.findViewById(R.id.home_joke_words_comment_layout));
            this.home_joke_words_share_layout = ((LinearLayout) paramView.findViewById(R.id.home_joke_words_share_layout));
        }
    }
}

