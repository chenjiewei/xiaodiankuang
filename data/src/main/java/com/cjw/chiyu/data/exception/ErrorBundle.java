package com.cjw.chiyu.data.exception;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:54
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public abstract interface ErrorBundle {
    public abstract Exception getException();

    public abstract String getExceptionMessage();
}
