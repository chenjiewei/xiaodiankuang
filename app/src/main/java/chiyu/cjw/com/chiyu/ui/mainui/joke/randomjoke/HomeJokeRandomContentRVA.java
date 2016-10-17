package chiyu.cjw.com.chiyu.ui.mainui.joke.randomjoke;

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
import com.cjw.chiyu.common.log.LogPoxy;
import com.cjw.chiyu.common.utils.RuningExceptionUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;
import chiyu.cjw.com.chiyu.ui.mainui.joke.homepage.HomeJokeHomeContentRecyclerViewAdapter;

/**
 * Created: chnejiewei
 * data: 2016/9/10 22:02
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeJokeRandomContentRVA extends HomeJokeHomeContentRecyclerViewAdapter {
    private static final String TAG = "HomeJokeRandomContentRVA";
    protected static final int RECYCLERVIEW_PIC = 3;
    protected static final int RECYCLERVIEW_WORDS = 2;
    private List<HomeUIJokeModel> dataContent_list = null;
    private Context mContext;

    public HomeJokeRandomContentRVA(Context paramContext) {
        this.mContext = paramContext.getApplicationContext();
    }

    private void checkData() {
        if (this.dataContent_list == null) {
            RuningExceptionUtils.throwsRunException("HomeJokeWordsContentRVA == null");
        }
    }

    protected void bindViewHolderMethod(final RecyclerView.ViewHolder paramViewHolder, int paramInt) {
        LogPoxy.e(TAG, "chenjiewei", "bindViewHolderMethod");
        checkData();
        if (paramViewHolder instanceof WordsViewHolder) {
            HomeUIJokeModel localHomeUIJokeModel2 = (HomeUIJokeModel) this.dataContent_list.get(paramInt);
            if (this.onItemClickListener != null) {
                paramViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                        int i = paramViewHolder.getLayoutPosition();
                        HomeJokeRandomContentRVA.this.onItemClickListener.onItemClick(paramViewHolder.itemView, i);
                    }
                });
            }
            ((WordsViewHolder) paramViewHolder).joke_content.setText(Html.fromHtml(localHomeUIJokeModel2.content));
            ((WordsViewHolder) paramViewHolder).user_nick.setText(localHomeUIJokeModel2.userNick);
            ((WordsViewHolder) paramViewHolder).cell_tv_one.setText(String.valueOf(localHomeUIJokeModel2.aggreeNum));
            ((WordsViewHolder) paramViewHolder).cell_tv_two.setText(String.valueOf(localHomeUIJokeModel2.againstNum));
            ((WordsViewHolder) paramViewHolder).cell_tv_three.setText(String.valueOf(localHomeUIJokeModel2.commentNum));
            ((WordsViewHolder) paramViewHolder).cell_tv_four.setText(String.valueOf(localHomeUIJokeModel2.shareNum));
            ((WordsViewHolder) paramViewHolder).user_origal_tv.setText(localHomeUIJokeModel2.origialPlace);
            if ("error".equals(localHomeUIJokeModel2.userHeadPic)) {
                Picasso.with(this.mContext).load(R.mipmap.cy_home_headimage).into(((WordsViewHolder) paramViewHolder).home_joke_words_id_userheadpic_small);
            }
        } else if (paramViewHolder instanceof PicViewHolder) {
            HomeUIJokeModel localHomeUIJokeModel1 = (HomeUIJokeModel) this.dataContent_list.get(paramInt);
            if (this.onItemClickListener != null) {
                paramViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View paramAnonymousView) {
                        int i = paramViewHolder.getLayoutPosition();
                        HomeJokeRandomContentRVA.this.onItemClickListener.onItemClick(paramViewHolder.itemView, i);
                    }
                });
            }
            ((PicViewHolder) paramViewHolder).joke_content.setText(Html.fromHtml(localHomeUIJokeModel1.contentTitle));
            ((PicViewHolder) paramViewHolder).user_nick.setText(localHomeUIJokeModel1.userNick);
            ((PicViewHolder) paramViewHolder).cell_tv_one.setText(String.valueOf(localHomeUIJokeModel1.aggreeNum));
            ((PicViewHolder) paramViewHolder).cell_tv_two.setText(String.valueOf(localHomeUIJokeModel1.againstNum));
            ((PicViewHolder) paramViewHolder).cell_tv_three.setText(String.valueOf(localHomeUIJokeModel1.commentNum));
            ((PicViewHolder) paramViewHolder).cell_tv_four.setText(String.valueOf(localHomeUIJokeModel1.shareNum));
            ((PicViewHolder) paramViewHolder).user_origal_tv.setText(localHomeUIJokeModel1.origialPlace);
            if (localHomeUIJokeModel1.mType == HomeUIJokeModel.JOKETYPE.GIF) {
                Glide.with(this.mContext).load(localHomeUIJokeModel1.content).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(((PicViewHolder) paramViewHolder).joke_Image_content);
            } else if (localHomeUIJokeModel1.mType == HomeUIJokeModel.JOKETYPE.PICTURE) {
                Glide.with(this.mContext).load(localHomeUIJokeModel1.content).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.mipmap.cy_pic_load_error).crossFade(1000).into(((PicViewHolder) paramViewHolder).joke_Image_content);
            }
            if ("error".equals(localHomeUIJokeModel1.userHeadPic)) {
                Picasso.with(this.mContext).load(R.mipmap.cy_home_headimage).into(((PicViewHolder) paramViewHolder).home_joke_words_id_userheadpic_small);
            }
        }
    }

    @Override
    protected RecyclerView.ViewHolder createViewHolderMethod(ViewGroup paramViewGroup, int paramInt) {
        LogPoxy.e(TAG, "chenjiewei", "createViewHolderMethod");
        RecyclerView.ViewHolder localWordsViewHolder;
        if (RECYCLERVIEW_WORDS == paramInt) {
            localWordsViewHolder = new WordsViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.cy_home_joke_words_recyitem_nomal, paramViewGroup, false));
        } else if (RECYCLERVIEW_PIC == paramInt) {
            localWordsViewHolder = new PicViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.cy_home_joke_pic_recyitem_nomal, paramViewGroup, false));
        } else {
            localWordsViewHolder = new BottomItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.cy_home_joke_words_recyitem_bottom, paramViewGroup, false));
        }

        return localWordsViewHolder;
    }

    public void setContent(ArrayList<HomeUIJokeModel> paramArrayList) {
        this.dataContent_list = paramArrayList;
    }

    protected int setRecyItemCount() {
        LogPoxy.e(TAG, "chenjiewei", "setRecyItemCount");
        checkData();
        if (this.dataContent_list.size() == 0) {
            return 0;
        }
        return 1 + this.dataContent_list.size();
    }

    protected int setViewItemType(int paramInt) {
        LogPoxy.e(TAG, "chenjiewei", "setViewItemType");
        checkData();
        if (paramInt + 1 == getItemCount()) {
            return RECYCLERVIEW_BOOM;
        }
        if ((this.dataContent_list.get(paramInt)).mType == HomeUIJokeModel.JOKETYPE.WORDS) {
            return RECYCLERVIEW_WORDS;
        }
        return RECYCLERVIEW_PIC;
    }

    private class BottomItemViewHolder extends RecyclerView.ViewHolder {
        public BottomItemViewHolder(View paramView) {
            super(paramView);
        }
    }

    private class PicViewHolder extends RecyclerView.ViewHolder {
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

        public PicViewHolder(View paramView) {
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

    private class WordsViewHolder extends RecyclerView.ViewHolder {
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

        public WordsViewHolder(View paramView) {
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
