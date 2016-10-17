package chiyu.cjw.com.chiyu.manager.data.joke;

import com.cjw.chiyu.data.dataimpl.joke.juhejokes.JHJokeOperate;
import com.cjw.chiyu.data.datainterface.ContentListener;
import com.cjw.chiyu.data.datamodel.BaseModel;

import java.util.ArrayList;
import java.util.List;

import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;
import chiyu.cjw.com.chiyu.manager.utils.jokeObtainDataUtils;
import chiyu.cjw.com.chiyu.ui.mainui.joke.ConstructionBean;
import chiyu.cjw.com.chiyu.ui.mainui.joke.JokeDataChangeListener;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:24
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class JokePicAndGif extends BaseJokeHandler {
    private static volatile JokePicAndGif mJokePicAndGif = null;
    private ArrayList<HomeUIJokeModel> mHomeUIJokeModelList = new ArrayList();

    public static JokePicAndGif newInstances() {
        if (mJokePicAndGif == null) {
            synchronized (JokePicAndGif.class) {
                if (mJokePicAndGif == null) {
                    mJokePicAndGif = new JokePicAndGif();
                }
            }
        }
        return mJokePicAndGif;
    }

    @Override
    public void getData(BaseJokeHandler.ObtainDataType paramObtainDataType, final JokeDataChangeListener paramJokeDataChangeListener) {
        if (paramObtainDataType == BaseJokeHandler.ObtainDataType.LOAD) {
            JHJokeOperate.gainJokePicData(new ContentListener() {
                public void getComtend(List<BaseModel> paramAnonymousList) {
                    ArrayList localArrayList = ConstructionBean.constructionCardBean(paramAnonymousList);
                    JokePicAndGif.this.mHomeUIJokeModelList.addAll(localArrayList);
                    paramJokeDataChangeListener.loadJoke(JokePicAndGif.this.mHomeUIJokeModelList);
                }

                public void requsetFaile(String paramAnonymousString) {
                    paramJokeDataChangeListener.loadJokeFaile(paramAnonymousString);
                }
            }, jokeObtainDataUtils.getJokeWordSTime(), jokeObtainDataUtils.getJokeWordSPage());
        } else if (paramObtainDataType == BaseJokeHandler.ObtainDataType.REFRESH) {
            this.mHomeUIJokeModelList.clear();
            JHJokeOperate.gainJokePicData(new ContentListener() {
                public void getComtend(List<BaseModel> paramAnonymousList) {
                    mHomeUIJokeModelList = ConstructionBean.constructionCardBean(paramAnonymousList);
                    paramJokeDataChangeListener.refreshJoke(mHomeUIJokeModelList);
                }

                public void requsetFaile(String paramAnonymousString) {
                    paramJokeDataChangeListener.refreshJokeFaile(paramAnonymousString);
                }
            }, jokeObtainDataUtils.getJokeWordSTime(), jokeObtainDataUtils.getJokeWordSPage());
        }


    }
}

