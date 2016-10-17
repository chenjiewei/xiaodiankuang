package com.cjw.chiyu.data.dataimpl.joke.juhejokes;

import com.cjw.chiyu.data.dataconfig.ErrorCodeConstants;
import com.cjw.chiyu.data.dataimpl.okhttputils.OkHttpManager;
import com.cjw.chiyu.data.datainterface.ContentListener;
import com.cjw.chiyu.data.datamodel.BaseModel;
import com.cjw.chiyu.data.utils.JokeJsonToModel;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:47
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class JHJokeRandomRunnable implements Runnable {
    private ContentListener mContentListener;
    private String mContentUrl;
    private Map<String, String> mParamsMap = null;
    private int[] mtype;

    public JHJokeRandomRunnable(ContentListener paramContentListener, String contentUrl, Map<String, String> paramMap, int[] type) {
        this.mContentListener = paramContentListener;
        this.mContentUrl = contentUrl;
        this.mParamsMap = paramMap;
        this.mtype = type;
    }

    public void run() {
        String str1 = OkHttpManager.getInstance().requestSyn(this.mContentUrl, OkHttpManager.TYPE_GET, this.mParamsMap);
        if (str1 == null) {
            this.mContentListener.requsetFaile(ErrorCodeConstants.ERROR_OKHTTP_ONLINE_FAILURE);
            return;
        }
        List<BaseModel> wordsList = JokeJsonToModel.analyzeJHJsonByRandom(str1, this.mtype[0], this.mContentListener);
        if (wordsList == null) {
            return;
        }
        if (wordsList.size() == 0) {
            this.mContentListener.requsetFaile(ErrorCodeConstants.ERROR_OKHTTP_DATA_EMPTY);
            return;
        }
        this.mParamsMap.put("type", "pic");
        String str2 = OkHttpManager.getInstance().requestSyn(this.mContentUrl, OkHttpManager.TYPE_GET, this.mParamsMap);
        if (str2 == null) {
            this.mContentListener.requsetFaile(ErrorCodeConstants.ERROR_OKHTTP_ONLINE_FAILURE);
            return;
        }
        List<BaseModel> othersList = JokeJsonToModel.analyzeJHJsonByRandom(str2, this.mtype[1], this.mContentListener);
        if (othersList == null) {
            return;
        }
        if (othersList.size() == 0) {
            this.mContentListener.requsetFaile(ErrorCodeConstants.ERROR_OKHTTP_DATA_EMPTY);
            return;
        }
        wordsList.addAll(othersList);
        Collections.shuffle(wordsList);
        this.mContentListener.getComtend(wordsList);
    }
}
