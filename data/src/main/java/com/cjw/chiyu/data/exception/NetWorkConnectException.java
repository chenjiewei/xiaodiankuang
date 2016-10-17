package com.cjw.chiyu.data.exception;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:55
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class NetWorkConnectException
        extends Exception {
    public NetWorkConnectException() {
    }

    public NetWorkConnectException(String paramString) {
        super(paramString);
    }

    public NetWorkConnectException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }

    public NetWorkConnectException(Throwable paramThrowable) {
        super(paramThrowable);
    }
}
