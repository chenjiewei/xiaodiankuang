package com.cjw.chiyu.data.exception;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:55
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String paramString) {
        super(paramString);
    }

    public UserNotFoundException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }

    public UserNotFoundException(Throwable paramThrowable) {
        super(paramThrowable);
    }
}