package com.cjw.chiyu.data.dataimpl.joke.yiyuanjokes;

import com.cjw.chiyu.data.dataconfig.YiYuanConfig;
import com.cjw.chiyu.data.datainterface.ContentListener;
import com.cjw.chiyu.data.threadpool.ThreadPoolUtils;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:50
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description: 数据层暴露的接口方法
 */
public class YYJokeOperate {
    /**
     * 获取gif数据
     *
     * @param paramContentListener
     * @param time
     * @param pageInt
     */
    public static void gainJokeGifData(ContentListener paramContentListener, String time, int pageInt) {
        submitTask(paramContentListener, YiYuanConfig.getYy_showapi_jokegifurl, time, pageInt, YiYuanConfig.everytime_request_num);
    }

    /**
     * 获取图片数据
     *
     * @param paramContentListener
     * @param time
     * @param pageInt
     */
    public static void gainJokePicData(ContentListener paramContentListener, String time, int pageInt) {
        submitTask(paramContentListener, YiYuanConfig.getYy_showapi_jokeimageurl, time, pageInt, YiYuanConfig.everytime_request_num);
    }

    /**
     * 获取文本数据
     *
     * @param paramContentListener
     * @param time
     * @param pageInt
     */
    public static void gainJokeWordsData(ContentListener paramContentListener, String time, int pageInt) {
        submitTask(paramContentListener, YiYuanConfig.getYy_showapi_jokewordurl, time, pageInt, YiYuanConfig.everytime_request_num);
    }

    /**
     * 将任务仍至线程池中
     *
     * @param paramContentListener
     * @param contentUrl
     * @param time
     * @param pageInt
     * @param maxItemNum
     */
    private static void submitTask(ContentListener paramContentListener, String contentUrl, String time, int pageInt, int maxItemNum) {
        YYJokeRunnable yyJokeRunnable = new YYJokeRunnable(paramContentListener, contentUrl, time, pageInt, maxItemNum);
        if (yyJokeRunnable == null) {
            return;
        }
        ThreadPoolUtils.newInstances().submitRunTask(yyJokeRunnable);
    }
}
