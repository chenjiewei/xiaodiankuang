package com.cjw.chiyu.data.dataimpl.joke.juhejokes;

import com.cjw.chiyu.data.dataconfig.ErrorCodeConstants;
import com.cjw.chiyu.data.dataimpl.okhttputils.OkHttpManager;
import com.cjw.chiyu.data.datainterface.ContentListener;
import com.cjw.chiyu.data.datamodel.BaseModel;
import com.cjw.chiyu.data.datamodel.Joke;
import com.cjw.chiyu.data.utils.JokeJsonToModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:47
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description: 获取除了随机数据的数据
 */
public class JHJokeNomalRunnable implements Runnable {
    private ContentListener mContentListener;
    private String mContentUrl;
    private Map<String, String> mParamsMap = null;
    private int mtype;

    public JHJokeNomalRunnable(ContentListener paramContentListener, String contentUrl, Map<String, String> paramMap, int type) {
        this.mContentListener = paramContentListener;
        this.mContentUrl = contentUrl;
        this.mParamsMap = paramMap;
        this.mtype = type;
    }

    public void run() {
        String result = OkHttpManager.getInstance().requestSyn(this.mContentUrl, OkHttpManager.TYPE_GET, this.mParamsMap);
        if (result == null) {
            this.mContentListener.requsetFaile(ErrorCodeConstants.ERROR_OKHTTP_ONLINE_FAILURE);
            return;
        }
        List<BaseModel> list = JokeJsonToModel.analyzeJHJsonByWords(result, this.mtype, this.mContentListener);
        if (list == null) {
            return;
        }
        if (list.size() == 0) {
            this.mContentListener.requsetFaile(ErrorCodeConstants.ERROR_OKHTTP_DATA_EMPTY);
            return;
        }
        this.mContentListener.getComtend(list);
    }
}

