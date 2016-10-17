package chiyu.cjw.com.chiyu.manager.data.joke;

import com.cjw.chiyu.data.dataimpl.joke.juhejokes.JHJokeOperate;
import com.cjw.chiyu.data.datainterface.ContentListener;
import com.cjw.chiyu.data.datamodel.BaseModel;

import java.util.ArrayList;
import java.util.List;

import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;
import chiyu.cjw.com.chiyu.ui.mainui.joke.ConstructionBean;
import chiyu.cjw.com.chiyu.ui.mainui.joke.JokeDataChangeListener;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:25
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class JokeRandom extends BaseJokeHandler {
    private static volatile JokeRandom mJokeRandom = null;
    private ArrayList<HomeUIJokeModel> mHomeUIJokeModelList = new ArrayList();

    public static JokeRandom newInstances() {
        if (mJokeRandom == null) {
            synchronized (JokeRandom.class) {
                if (mJokeRandom == null) {
                    mJokeRandom = new JokeRandom();
                }
            }
        }
        return mJokeRandom;
    }

    public void getData(BaseJokeHandler.ObtainDataType paramObtainDataType, final JokeDataChangeListener paramJokeDataChangeListener) {
        if (paramObtainDataType == BaseJokeHandler.ObtainDataType.LOAD) {
            JHJokeOperate.gainJokeRandomData(new ContentListener() {
                public void getComtend(List<BaseModel> paramAnonymousList) {
                    ArrayList localArrayList = ConstructionBean.constructionCardBean(paramAnonymousList);
                    JokeRandom.this.mHomeUIJokeModelList.addAll(localArrayList);
                    paramJokeDataChangeListener.loadJoke(JokeRandom.this.mHomeUIJokeModelList);
                }

                public void requsetFaile(String paramAnonymousString) {
                    paramJokeDataChangeListener.loadJokeFaile(paramAnonymousString);
                }
            });
        } else if (paramObtainDataType == BaseJokeHandler.ObtainDataType.REFRESH) {
            this.mHomeUIJokeModelList.clear();
            JHJokeOperate.gainJokeRandomData(new ContentListener() {
                public void getComtend(List<BaseModel> paramAnonymousList) {
                    mHomeUIJokeModelList = ConstructionBean.constructionCardBean(paramAnonymousList);
                    paramJokeDataChangeListener.refreshJoke(JokeRandom.this.mHomeUIJokeModelList);
                }

                public void requsetFaile(String paramAnonymousString) {
                    paramJokeDataChangeListener.refreshJokeFaile(paramAnonymousString);
                }
            });
        }

    }
}
