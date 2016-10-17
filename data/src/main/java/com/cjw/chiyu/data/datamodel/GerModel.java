package com.cjw.chiyu.data.datamodel;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:53
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class GerModel<T> {
    private T model;

    public GerModel(T paramT) {
        this.model = paramT;
    }

    public T getModel() {
        return this.model;
    }
}