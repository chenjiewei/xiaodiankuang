package chiyu.cjw.com.chiyu.ui.mainui.joke;

import java.util.ArrayList;

import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;

/**
 * Created: chnejiewei
 * data: 2016/9/10 21:55
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public interface JokeDataChangeListener {
    void loadJoke(ArrayList<HomeUIJokeModel> paramArrayList);

    void loadJokeFaile(String paramString);

    void refreshJoke(ArrayList<HomeUIJokeModel> paramArrayList);

    void refreshJokeFaile(String paramString);
}

