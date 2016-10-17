package com.cjw.chiyu.common.utils;

import java.util.List;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:07
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:  对list集合属性获取
 */
public class ListUtils {
    /**
     * 获取list的大小
     *
     * @param paramList
     * @param <V>
     * @return
     */
    public static <V> int getSize(List<V> paramList) {
        if (paramList == null) {
            return 0;
        }
        return paramList.size();
    }

    /**
     * 判断list是否为空
     *
     * @param paramList
     * @param <V>
     * @return
     */
    public static <V> boolean isEmpty(List<V> paramList) {
        return (paramList == null) || (paramList.size() == 0);
    }
}