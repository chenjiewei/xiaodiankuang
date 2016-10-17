package com.cjw.chiyu.data.exception;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:55
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class RepositoryErrorBundle
        implements ErrorBundle {
    private Exception mException;

    public RepositoryErrorBundle(Exception paramException) {
        this.mException = paramException;
    }

    public Exception getException() {
        return this.mException;
    }

    public String getExceptionMessage() {
        String str = "";
        if (this.mException != null) {
            str = this.mException.getMessage();
        }
        return str;
    }
}
