package com.cjw.chiyu.data.datainterface;

import com.cjw.chiyu.data.datamodel.BaseModel;

import java.util.List;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:52
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */

public abstract class ContentListener {
    public abstract void getComtend(List<BaseModel> paramList);

    public abstract void requsetFaile(String paramString);
}

