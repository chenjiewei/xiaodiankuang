package com.cjw.chiyu.data.dataimpl.okhttputils;

/**
 * Created: chnejiewei
 * data: 2016/9/11 14:33
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public abstract interface IRequsetCallBack<T>
{
    public abstract void onReqFailed(String paramString);

    public abstract void onReqSuccess(T paramT);
}