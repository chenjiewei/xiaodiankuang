package chiyu.cjw.com.chiyu.ui.mainui.joke.pickjoke;

import java.util.ArrayList;

import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;

/**
 * Created: chnejiewei
 * data: 2016/9/10 22:00
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public interface IHomeJokeFragmentPickView {
    void hideLoadLayout();

    void hideSwipeRefreshLayout();

    void jumpDetailPage();

    void paddingContentToView(ArrayList<HomeUIJokeModel> paramArrayList);

    void showRefreshFaile(String paramString);
}
