package chiyu.cjw.com.chiyu.ui.uiutils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cjw.chiyu.common.utils.RuningExceptionUtils;

import java.util.ArrayList;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:04
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class FragmentPagerAdapterUtils extends FragmentPagerAdapter {
    private static final String TAG = "FragmentPagerAdapterUtils";
    private int COUNT = -1;
    private Context mContext;
    private ArrayList<Fragment> mFragmentList = null;
    private ArrayList<String> mStrList = null;

    public FragmentPagerAdapterUtils(FragmentManager paramFragmentManager, Context paramContext) {
        super(paramFragmentManager);
        this.mContext = paramContext;
    }

    /**
     * 得到fragment的个数
     *
     * @return
     */
    public int getCount() {
        if (this.COUNT == -1) {
            RuningExceptionUtils.throwsRunException("FragmentPagerAdapterUtils COUNT == -1");
        }
        return this.COUNT;
    }

    /**
     * 得到Item
     *
     * @param paramInt
     * @return
     */
    public Fragment getItem(int paramInt) {
        if (this.mFragmentList == null) {
            RuningExceptionUtils.throwsRunException("FragmentPagerAdapterUtils，mFragmentList == null");
            return null;
        }
        return (Fragment) this.mFragmentList.get(paramInt);
    }

    /**
     * 得到页数的title
     *
     * @param paramInt
     * @return
     */
    public CharSequence getPageTitle(int paramInt) {
        if (this.mStrList == null) {
            RuningExceptionUtils.throwsRunException("mStrList == null");
            return null;
        }
        return (CharSequence) this.mStrList.get(paramInt);
    }

    /**
     * 数据初始化
     *
     * @param paramArrayList
     * @param paramArrayList1
     */
    public void initData(ArrayList<String> paramArrayList, ArrayList<Fragment> paramArrayList1) {
        this.mStrList = paramArrayList;
        this.mFragmentList = paramArrayList1;
        if (this.mStrList.size() != this.mFragmentList.size()) {
            RuningExceptionUtils.throwsRunException("FragmentPagerAdapterUtils，initData，mStrList.size() != mFragmentList.size()");
        }
        this.COUNT = this.mStrList.size();
    }
}
