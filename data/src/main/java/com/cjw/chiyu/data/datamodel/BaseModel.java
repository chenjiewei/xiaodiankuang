package com.cjw.chiyu.data.datamodel;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:53
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class BaseModel
{
    protected GerModel mGerModel;

    public GerModel getmGerModel()
    {
        return this.mGerModel;
    }

    protected void initGerModel(GerModel gerModel)
    {
        this.mGerModel = gerModel;
    }
}
