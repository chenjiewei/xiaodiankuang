package chiyu.cjw.com.chiyu.manager.data.joke;

import chiyu.cjw.com.chiyu.ui.mainui.joke.JokeDataChangeListener;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:24
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public abstract class BaseJokeHandler {
    public abstract void getData(ObtainDataType paramObtainDataType, JokeDataChangeListener paramJokeDataChangeListener);

    public enum ObtainDataType {
        LOAD, REFRESH;
    }
}
