package com.cjw.chiyu.data.dataimpl.joke.yiyuanjokes;

import com.cjw.chiyu.data.dataconfig.ErrorCodeConstants;
import com.cjw.chiyu.data.dataconfig.YiYuanConfig;
import com.cjw.chiyu.data.datainterface.ContentListener;
import com.cjw.chiyu.data.datamodel.BaseModel;
import com.cjw.chiyu.data.utils.JokeJsonToModel;
import com.show.api.ShowApiRequest;

import java.util.List;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:50
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description: 易源数据的获取
 */
public class YYJokeRunnable implements Runnable {
    private ContentListener mContentListener;
    private String mContentUrl;
    private int mPage;
    private String mTime;
    private int maxResult;

    public YYJokeRunnable(ContentListener paramContentListener, String contentUrl, String time, int pageInt, int maxItemNum) {
        this.mContentListener = paramContentListener;
        this.mContentUrl = contentUrl;
        this.mTime = time;
        this.mPage = pageInt;
        this.maxResult = maxItemNum;
    }

    @Override
    public void run() {
        String result = new ShowApiRequest(this.mContentUrl, YiYuanConfig.yy_showapi_appid, YiYuanConfig.yy_showapi_appsecret).addTextPara("time", this.mTime).addTextPara("page", String.valueOf(this.mPage)).addTextPara("maxResult", String.valueOf(this.maxResult)).post();
        List<BaseModel> listResult = JokeJsonToModel.analyzeYYJson(result);

        if (listResult.size() == 0) {
            this.mContentListener.requsetFaile(ErrorCodeConstants.ERRoR_Content);
        }
        this.mContentListener.getComtend(listResult);
    }
}
