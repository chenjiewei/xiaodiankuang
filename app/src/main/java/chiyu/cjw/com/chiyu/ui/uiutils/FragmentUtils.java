package chiyu.cjw.com.chiyu.ui.uiutils;

import com.cjw.chiyu.common.log.LogPoxy;

import java.util.HashMap;
import java.util.Map;

import chiyu.cjw.com.chiyu.ui.base.BaseFragment;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:05
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class FragmentUtils {
    private static final String TAG = "FragmentUtils";
    private Map<String, BaseFragment> mFragmentMap = new HashMap<>();

    public BaseFragment createFragment(java.lang.Class<?> paramClass) {
        BaseFragment baseFragment = null;
        String className = paramClass.getName();
        if (mFragmentMap.containsKey(className)) {
            return mFragmentMap.get(className);
        }
        try {
            baseFragment = (BaseFragment) Class.forName(className).newInstance();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            LogPoxy.e(TAG, "createFragment", ex.toString());
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            LogPoxy.e(TAG, "createFragment", ex.toString());
        } catch (InstantiationException ex) {
            ex.printStackTrace();
            LogPoxy.e(TAG, "createFragment", ex.toString());
        }
        mFragmentMap.put(className, baseFragment);
        return baseFragment;
    }
}

