package com.cjw.chiyu.data.dataimpl.joke.juhejokes;

import com.cjw.chiyu.data.dataconfig.JuHeConfig;
import com.cjw.chiyu.data.datainterface.ContentListener;
import com.cjw.chiyu.data.threadpool.ThreadPoolUtils;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:47
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class JHJokeOperate {
    public static final int SOURCETYPE_OTHER = 2;
    public static final int SOURCETYPE_WORDS = 1;

    /**
     * 获取聚合图片数据
     *
     * @param paramContentListener
     * @param time
     * @param pageInt
     */
    public static void gainJokePicData(ContentListener paramContentListener, String time, int pageInt) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("sort", "asc");
        paramMap.put("page", "" + pageInt);
        paramMap.put("pagesize", "" + JuHeConfig.everytime_request_num);
        paramMap.put("time", time);
        paramMap.put("key", JuHeConfig.JH_showapi_appsecret);
        submitTask(new JHJokeNomalRunnable(paramContentListener, JuHeConfig.getJH_showapi_jokegifurl, paramMap, SOURCETYPE_OTHER));
    }

    /**
     * 获取随机数据
     *
     * @param paramContentListener
     */
    public static void gainJokeRandomData(ContentListener paramContentListener) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("key", JuHeConfig.JH_showapi_appsecret);
        submitTask(new JHJokeRandomRunnable(paramContentListener, JuHeConfig.getJH_showapi_random_data, paramMap, new int[]{SOURCETYPE_WORDS, SOURCETYPE_OTHER}));
    }

    /**
     * 获取文本数据
     *
     * @param paramContentListener
     * @param time
     * @param page
     */
    public static void gainJokeWordsData(ContentListener paramContentListener, String time, int page) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("sort", "desc");
        paramMap.put("page", "" + page);
        paramMap.put("pagesize", "" + JuHeConfig.everytime_request_num);
        paramMap.put("time", time);
        paramMap.put("key", JuHeConfig.JH_showapi_appsecret);
        submitTask(new JHJokeNomalRunnable(paramContentListener, JuHeConfig.getJH_showapi_jokeimageurl, paramMap, SOURCETYPE_WORDS));
    }

    /**
     * 将任务仍至线程池中
     *
     * @param paramRunnable
     */
    private static void submitTask(Runnable paramRunnable) {
        if (paramRunnable == null) {
            return;
        }
        ThreadPoolUtils.newInstances().submitRunTask(paramRunnable);
    }
}
