package chiyu.cjw.com.chiyu.ui.mainui.joke.pickjoke;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cjw.chiyu.common.utils.RuningExceptionUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;
import chiyu.cjw.com.chiyu.ui.mainui.joke.homepage.HomeJokeHomeContentRecyclerViewAdapter;

/**
 * Created: chnejiewei
 * data: 2016/9/10 21:59
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokePicContentRVA extends HomeJokeHomeContentRecyclerViewAdapter {
    private List<HomeUIJokeModel> dataContent_list = null;
    private Context mContext;

    public HomeJokePicContentRVA(Context paramContext) {
        this.mContext = paramContext.getApplicationContext();
    }

    private void checkData() {
        if (this.dataContent_list == null) {
            RuningExceptionUtils.throwsRunException("HomeJokeWordsContentRVA == null");
        }
    }

    protected void bindViewHolderMethod(final RecyclerView.ViewHolder paramViewHolder, int position) {
        checkData();
        if ((paramViewHolder instanceof NomalItemViewHolder)) {
            HomeUIJokeModel homeUIJokeModel = this.dataContent_list.get(position);
            if (this.onItemClickListener != null) {
                paramViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                        int i = paramViewHolder.getLayoutPosition();
                        HomeJokePicContentRVA.this.onItemClickListener.onItemClick(paramViewHolder.itemView, i);
                    }
                });
            }
            ((NomalItemViewHolder) paramViewHolder).joke_content.setText(Html.fromHtml(homeUIJokeModel.contentTitle));
            ((NomalItemViewHolder) paramViewHolder).user_nick.setText(homeUIJokeModel.userNick);
            ((NomalItemViewHolder) paramViewHolder).cell_tv_one.setText(String.valueOf(homeUIJokeModel.aggreeNum));
            ((NomalItemViewHolder) paramViewHolder).cell_tv_two.setText(String.valueOf(homeUIJokeModel.againstNum));
            ((NomalItemViewHolder) paramViewHolder).cell_tv_three.setText(String.valueOf(homeUIJokeModel.commentNum));
            ((NomalItemViewHolder) paramViewHolder).cell_tv_four.setText(String.valueOf(homeUIJokeModel.shareNum));
            ((NomalItemViewHolder) paramViewHolder).user_origal_tv.setText(homeUIJokeModel.origialPlace);
            if (homeUIJokeModel.mType == HomeUIJokeModel.JOKETYPE.GIF) {
                Glide.with(this.mContext).load(homeUIJokeModel.content).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(((NomalItemViewHolder) paramViewHolder).joke_Image_content);
            } else if (homeUIJokeModel.mType == HomeUIJokeModel.JOKETYPE.PICTURE) {
                Glide.with(this.mContext).load(homeUIJokeModel.content).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.mipmap.cy_pic_load_error).crossFade(1000).into(((NomalItemViewHolder) paramViewHolder).joke_Image_content);
            }
            if ("error".equals(homeUIJokeModel.userHeadPic)) {
                Picasso.with(this.mContext).load(R.mipmap.cy_home_headimage).into(((NomalItemViewHolder) paramViewHolder).home_joke_words_id_userheadpic_small);
            } else {
                Picasso.with(this.mContext).load(homeUIJokeModel.userHeadPic).into(((NomalItemViewHolder) paramViewHolder).home_joke_words_id_userheadpic_small);
            }
        }
    }

    protected RecyclerView.ViewHolder createViewHolderMethod(ViewGroup paramViewGroup, int typeInt) {
        RecyclerView.ViewHolder viewHolder;
        if (typeInt == RECYCLERVIEW_ITEM) {
            viewHolder = new NomalItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.cy_home_joke_pic_recyitem_nomal, paramViewGroup, false));
        } else {
            viewHolder = new BottomItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.cy_home_joke_words_recyitem_bottom, paramViewGroup, false));
        }
        return viewHolder;
    }

    public void setContent(ArrayList<HomeUIJokeModel> paramArrayList) {
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

    private class BottomItemViewHolder extends RecyclerView.ViewHolder {
        public BottomItemViewHolder(View paramView) {
            super(paramView);
        }
    }

    private class NomalItemViewHolder extends RecyclerView.ViewHolder {
        public TextView cell_tv_four;
        public TextView cell_tv_one;
        public TextView cell_tv_three;
        public TextView cell_tv_two;
        public LinearLayout home_joke_words_against_layout;
        public LinearLayout home_joke_words_aggree_layout;
        public LinearLayout home_joke_words_comment_layout;
        public ImageView home_joke_words_id_userheadpic_small;
        public LinearLayout home_joke_words_share_layout;
        public ImageView joke_Image_content;
        public TextView joke_content;
        public TextView user_nick;
        public TextView user_origal_tv;

        public NomalItemViewHolder(View paramView) {
            super(paramView);
            this.home_joke_words_id_userheadpic_small = ((ImageView) paramView.findViewById(R.id.home_joke_words_id_userheadpic_small));
            this.joke_Image_content = ((ImageView) paramView.findViewById(R.id.joke_Image_content));
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

