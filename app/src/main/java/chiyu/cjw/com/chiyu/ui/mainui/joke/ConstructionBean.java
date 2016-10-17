package chiyu.cjw.com.chiyu.ui.mainui.joke;

import com.cjw.chiyu.data.datamodel.BaseModel;
import com.cjw.chiyu.data.datamodel.Joke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import chiyu.cjw.com.chiyu.manager.model.homeuimodel.HomeUIJokeModel;

/**
 * Created: chnejiewei
 * data: 2016/9/10 21:54
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class ConstructionBean {
    public static ArrayList<HomeUIJokeModel> constructionCardBean(List<BaseModel> paramList) {
        ArrayList localArrayList;

        localArrayList = new ArrayList();
        Iterator iterator = paramList.iterator();
        while (iterator.hasNext()) {
            BaseModel localBaseModel = (BaseModel) iterator.next();
            HomeUIJokeModel homeUIJokeModel = new HomeUIJokeModel();
            Joke localJoke = (Joke) localBaseModel.getmGerModel().getModel();
            homeUIJokeModel.aggreeNum = 210;
            homeUIJokeModel.againstNum = 20;
            homeUIJokeModel.commentNum = 103;
            homeUIJokeModel.shareNum = 141;
            homeUIJokeModel.content = localJoke.getmJoke_content();
            homeUIJokeModel.jokeId = localJoke.getmJoke_id();
            homeUIJokeModel.origialPlace = "吃瓜群众";
            homeUIJokeModel.userNick = "来打死爹！！";
            homeUIJokeModel.isHadGodComment = false;
            homeUIJokeModel.contentTitle = localJoke.getmJoke_title();
            homeUIJokeModel.mType = HomeUIJokeModel.JOKETYPE.values()[(-1 + localJoke.getmJoke_type())];
            homeUIJokeModel.userHeadPic = "error";
            localArrayList.add(homeUIJokeModel);
        }

        return localArrayList;
    }
}
