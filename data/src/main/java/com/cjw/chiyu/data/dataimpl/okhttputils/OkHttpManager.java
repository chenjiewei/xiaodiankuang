package com.cjw.chiyu.data.dataimpl.okhttputils;


import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.cjw.chiyu.common.log.LogPoxy;
import com.cjw.chiyu.data.dataconfig.ErrorCodeConstants;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created: chnejiewei
 * data: 2016/9/11 14:34
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class OkHttpManager {
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final String TAG = OkHttpManager.class.getSimpleName();
    public static final int TYPE_GET = 0;
    public static final int TYPE_POST_FORM = 2;
    public static final int TYPE_POST_JSON = 1;
    private static volatile OkHttpManager mInstance;
    private OkHttpClient mOkHttpClient = null;
    /**
     * 私有构造方法
     */
    private OkHttpManager() {
        //初始化OkHttpClient
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();
    }


    /**
     * 单例获取
     *
     * @return
     */
    public static OkHttpManager getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 统一为请求添加头信息
     *
     * @return
     */
    private Request.Builder addHeaders() {
        Request.Builder builder = new Request.Builder()
                .addHeader("Connection", "keep-alive")
                .addHeader("platform", "2")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE);
        return builder;
    }

    /**
     * okHttp同步请求统一入口
     *
     * @param actionUrl   接口地址
     * @param requestType 请求类型
     * @param paramsMap   请求的参数
     * @return
     */
    public String requestSyn(String actionUrl, int requestType, Map<String, String> paramsMap) {
        String resultStr = null;
        switch (requestType) {
            case TYPE_GET:
                resultStr = requestGetBySyn(actionUrl, paramsMap);
                break;
            case TYPE_POST_JSON:
                resultStr = requestPostBySyn(actionUrl, paramsMap);
                break;
            case TYPE_POST_FORM:
                resultStr = requestPostBySynWithForm(actionUrl, paramsMap);
                break;
        }
        return resultStr;
    }

    /**
     * okHttp get同步请求
     *
     * @param actionUrl
     * @param paramsMap
     * @return
     */
    private String requestGetBySyn(String actionUrl, Map<String, String> paramsMap) {
        StringBuilder tempParams = new StringBuilder();
        String result = null;
        try {
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                //对参数进行URLEncoder
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //补全请求地址
            String requestUrl = String.format("%s?%s", actionUrl, tempParams.toString());
            //创建一个请求
            Request request = addHeaders().url(requestUrl).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            final Response response = call.execute();
            result = response.body().string();
            LogPoxy.i(TAG, "requestGetBySyn == ", result);
        } catch (Exception e) {
            LogPoxy.e(TAG, "requestGetBySyn", e.toString());
        }
        return result;
    }

    /**
     * okHttp post同步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     */
    private String requestPostBySyn(String actionUrl, Map<String, String> paramsMap) {
        String result = null;
        try {
            //处理参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            //补全请求地址
            String requestUrl = String.format("%s", actionUrl);
            //生成参数
            String params = tempParams.toString();
            //创建一个请求实体对象 RequestBody
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            //创建一个请求
            final Request request = addHeaders().url(requestUrl).post(body).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            Response response = call.execute();
            //请求执行成功
            if (response.isSuccessful()) {
                //获取返回数据 可以是String，bytes ,byteStream
                result = response.body().string();
                LogPoxy.i(TAG, "requestPostBySyn", "result == ", result);
            }
        } catch (Exception e) {
            LogPoxy.e(TAG, "requestPostBySyn", e.toString());
        }
        return result;
    }

    /**
     * okHttp post同步请求表单提交
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     */
    private String requestPostBySynWithForm(String actionUrl, Map<String, String> paramsMap) {
        String result = null;
        try {
            //创建一个FormBody.Builder
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : paramsMap.keySet()) {
                //追加表单信息
                builder.add(key, paramsMap.get(key));
            }
            //生成表单实体对象
            RequestBody formBody = builder.build();
            //补全请求地址
            String requestUrl = String.format("%s", actionUrl);
            //创建一个请求
            final Request request = addHeaders().url(requestUrl).post(formBody).build();
            //创建一个Call
            final Call call = mOkHttpClient.newCall(request);
            //执行请求
            Response response = call.execute();
            if (response.isSuccessful()) {
                result = response.body().string();
                LogPoxy.i(TAG, "requestPostBySyn", "result == ", result);
            }
        } catch (Exception e) {
            LogPoxy.e(TAG, "requestPostBySyn", e.toString());
        }
        return result;
    }

    /**
     * okHttp异步请求统一入口
     *
     * @param actionUrl   接口地址
     * @param requestType 请求类型
     * @param paramsMap   请求参数
     * @param callBack    请求返回数据回调
     * @param <T>         数据泛型
     **/
    public <T> Call requestAsyn(String actionUrl, int requestType, Map<String, String> paramsMap, IRequsetCallBack<T> callBack) {
        Call call = null;
        switch (requestType) {
            case TYPE_GET:
                call = requestGetByAsyn(actionUrl, paramsMap, callBack);
                break;
            case TYPE_POST_JSON:
                call = requestPostByAsyn(actionUrl, paramsMap, callBack);
                break;
            case TYPE_POST_FORM:
                call = requestPostByAsynWithForm(actionUrl, paramsMap, callBack);
                break;
        }
        return call;
    }

    /**
     * okHttp get异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     * @return
     */
    private <T> Call requestGetByAsyn(String actionUrl, Map<String, String> paramsMap, final IRequsetCallBack<T> callBack) {
        StringBuilder tempParams = new StringBuilder();
        try {
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            String requestUrl = String.format("%s?%s", actionUrl, tempParams.toString());
            final Request request = addHeaders().url(requestUrl).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack(ErrorCodeConstants.ERROR_OKHTTP_ACCESS_FAILURE, callBack);
                    LogPoxy.e(TAG, "requestGetByAsyn , onFailure == ", e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        LogPoxy.i(TAG, "requestGetByAsyn , onResponse == ", string);
                        successCallBack((T) string, callBack);
                    } else {
                        failedCallBack(ErrorCodeConstants.ERROR_OKHTTP_ACCESS_FAILURE_SERVER_FAILURE, callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            LogPoxy.i(TAG, "requestGetByAsyn , Exception == ", e.toString());
        }
        return null;
    }

    /**
     * okHttp post异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     * @return
     */
    private <T> Call requestPostByAsyn(String actionUrl, Map<String, String> paramsMap, final IRequsetCallBack<T> callBack) {
        try {
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            String params = tempParams.toString();
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);
            String requestUrl = String.format("%s", actionUrl);
            final Request request = addHeaders().url(requestUrl).post(body).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack(ErrorCodeConstants.ERROR_OKHTTP_ACCESS_FAILURE, callBack);
                    LogPoxy.e(TAG, "requestPostByAsyn , onFailure == ", e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        successCallBack((T) string, callBack);
                        LogPoxy.e(TAG, "requestPostByAsyn , onResponse == ", string);
                    } else {
                        failedCallBack(ErrorCodeConstants.ERROR_OKHTTP_ACCESS_FAILURE_SERVER_FAILURE, callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            LogPoxy.i(TAG, "requestPostByAsyn , Exception == ", e.toString());
        }
        return null;
    }

    /**
     * okHttp post异步请求表单提交
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @param <T>       数据泛型
     * @return
     */
    private <T> Call requestPostByAsynWithForm(String actionUrl, Map<String, String> paramsMap, final IRequsetCallBack<T> callBack) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : paramsMap.keySet()) {
                builder.add(key, paramsMap.get(key));
            }
            RequestBody formBody = builder.build();
            String requestUrl = String.format("%s", actionUrl);
            final Request request = addHeaders().url(requestUrl).post(formBody).build();
            final Call call = mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    failedCallBack(ErrorCodeConstants.ERROR_OKHTTP_ACCESS_FAILURE, callBack);
                    LogPoxy.e(TAG, "requestPostByAsynWithForm , onFailure == ", e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        successCallBack((T) string, callBack);
                        LogPoxy.e(TAG, "requestPostByAsynWithForm , onResponse == ", string);
                    } else {
                        failedCallBack(ErrorCodeConstants.ERROR_OKHTTP_ACCESS_FAILURE_SERVER_FAILURE, callBack);
                    }
                }
            });
            return call;
        } catch (Exception e) {
            LogPoxy.i(TAG, "requestPostByAsynWithForm , Exception == ", e.toString());
        }
        return null;
    }

    /**
     * 统一处理成功信息
     *
     * @param result
     * @param callBack
     * @param <T>
     */
    private <T> void successCallBack(final T result, final IRequsetCallBack<T> callBack) {
        if (callBack != null) {
            callBack.onReqSuccess(result);
        }
    }

    /**
     * 统一处理失败信息
     *
     * @param errorMsg
     * @param callBack
     * @param <T>
     */
    private <T> void failedCallBack(final String errorMsg, final IRequsetCallBack<T> callBack) {
        if (callBack != null) {
            callBack.onReqFailed(errorMsg);
        }
    }
}

