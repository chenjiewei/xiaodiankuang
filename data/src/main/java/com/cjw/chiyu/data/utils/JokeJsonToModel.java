package com.cjw.chiyu.data.utils;

import com.cjw.chiyu.common.log.LogPoxy;
import com.cjw.chiyu.data.dataimpl.joke.juhejokes.JHJokeOperate;
import com.cjw.chiyu.data.datainterface.ContentListener;
import com.cjw.chiyu.data.datamodel.BaseModel;
import com.cjw.chiyu.data.datamodel.Joke;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:57
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:　JSON数据解析
 */
public class JokeJsonToModel {
    private static final String TAG = "JokeJsonToModel";

    /**
     * 解析聚合数据,随机的数据
     *
     * @param paramString
     * @param typeInt
     * @param paramContentListener
     * @return
     */
    public static List<BaseModel> analyzeJHJsonByRandom(String paramString, int typeInt, ContentListener paramContentListener) {
        List<BaseModel> resultList = null;
        JSONObject jsonObject = null;
        String id, title, content, ct;
        int type;
        Joke joke;
        JSONObject jsonObject_2 = null;
        try {
            jsonObject = new JSONObject(paramString);
            String reason = jsonObject.getString("reason");
            if (!reason.equals("success")) {
                paramContentListener.requsetFaile(reason);
                return resultList;
            }
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            resultList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); ++i) {
                jsonObject_2 = jsonArray.getJSONObject(i);
                ct = jsonObject_2.getString("unixtime");
                id = jsonObject_2.getString("hashId");
                if (typeInt == JHJokeOperate.SOURCETYPE_WORDS) {
                    // 如果是word
                    content = jsonObject_2.getString("content");
                    title = "";
                    type = Joke.JOKE_TYPE_WORDS;
                } else {
                    content = jsonObject_2.getString("url");
                    title = jsonObject_2.getString("content");
                    type = content.contains(".gif") ? Joke.JOKE_TYPE_GIFS : Joke.JOKE_TYPE_PICS;
                }
                joke = new Joke(id, title, type, content, ct);
                resultList.add(joke);
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
            LogPoxy.e(TAG, "analyzeJHJsonByRandom == ", ex.toString());
            resultList = null;
        }
        return resultList;
    }

    /**
     * 解析聚合数据的文字或者图片的资源
     *
     * @param paramString
     * @param typeInt
     * @param paramContentListener
     * @return
     */
    public static List<BaseModel> analyzeJHJsonByWords(String paramString, int typeInt, ContentListener paramContentListener) {
        List<BaseModel> resultList = null;
        JSONObject jsonObject = null;
        String id, title, content, ct;
        int type;
        Joke joke;
        JSONObject jsonObject_2 = null;
        try {
            jsonObject = new JSONObject(paramString);
            int error_code = jsonObject.getInt("error_code");
            String reason = jsonObject.getString("reason");
            if (error_code != 0) {
                paramContentListener.requsetFaile(reason);
                return resultList;
            }
            JSONArray jsonArray = jsonObject.getJSONObject("result").getJSONArray("data");
            resultList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); ++i) {
                jsonObject_2 = jsonArray.getJSONObject(i);
                ct = jsonObject_2.getString("updatetime");
                id = jsonObject_2.getString("hashId");
                if (typeInt == JHJokeOperate.SOURCETYPE_WORDS) {
                    // 如果是word
                    content = jsonObject_2.getString("content");
                    title = "";
                    type = Joke.JOKE_TYPE_WORDS;
                } else {
                    content = jsonObject_2.getString("url");
                    title = jsonObject_2.getString("content");
                    type = content.contains(".gif") ? Joke.JOKE_TYPE_GIFS : Joke.JOKE_TYPE_PICS;
                }
                joke = new Joke(id, title, type, content, ct);
                resultList.add(joke);
            }

        } catch (JSONException ex) {
            ex.printStackTrace();
            LogPoxy.e(TAG, "analyzeJHJsonByWords == ", ex.toString());
            resultList = null;
        }
        return resultList;
    }

    /**
     * 易源数据解析
     *
     * @param paramString
     * @return
     */
    public static List<BaseModel> analyzeYYJson(String paramString) {
        List<BaseModel> resultList = new ArrayList<>();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(paramString);
            int result_code = jsonObject.getInt("showapi_res_code");
            String result_error = jsonObject.getString("showapi_res_error");
            JSONObject jsonObject_1 = jsonObject.getJSONObject("showapi_res_body");
            JSONArray jsonArray = jsonObject_1.getJSONArray("contentlist");
            JSONObject jsonObject_2 = null;
            String id, title, content, ct;
            int type;
            Joke joke;
            for (int i = 0; i < jsonArray.length(); ++i) {
                jsonObject_2 = jsonArray.getJSONObject(i);
                id = jsonObject_2.getString("id");
                title = jsonObject_2.getString("title");
                type = jsonObject_2.getInt("type");
                ct = jsonObject_2.getString("ct");
                if (type == 1) {
                    // 说明是文本文件
                    content = jsonObject_2.getString("text");
                } else {
                    // 说明是图像文件
                    content = jsonObject_2.getString("img");
                }
                joke = new Joke(id, title, type, content, ct);
                resultList.add(joke);
            }
        } catch (JSONException je) {
            LogPoxy.e(TAG, "analyzeYYJson", je.toString());
            return resultList;
        }
        return resultList;

    }

}
