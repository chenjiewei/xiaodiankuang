package chiyu.cjw.com.chiyu.ui.welcomeui.introui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chiyu.cjw.com.chiyu.R;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:22
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class IntroFragment extends Fragment {
    public static final String ATG_KEY_RES_ID = "ATG_KEY_RES_ID";
    public static final String ATG_KEY_RES_MSG = "ATG_KEY_RES_MSG";
    private int mLayoutResId;
    private String msg;

    public static IntroFragment getInstances(int paramInt, String paramString) {
        IntroFragment localIntroFragment = new IntroFragment();
        Bundle localBundle = new Bundle();
        localBundle.putInt(ATG_KEY_RES_ID, paramInt);
        localBundle.putString(ATG_KEY_RES_MSG, paramString);
        localIntroFragment.setArguments(localBundle);
        return localIntroFragment;
    }

    public void onActivityCreated(@Nullable Bundle paramBundle) {
        super.onActivityCreated(paramBundle);
    }

    public void onCreate(@Nullable Bundle paramBundle) {
        super.onCreate(paramBundle);
        if ((getArguments() != null) && (getArguments().containsKey("ATG_KEY_RES_ID")) && (getArguments().containsKey("ATG_KEY_RES_MSG"))) {
            this.mLayoutResId = getArguments().getInt("ATG_KEY_RES_ID");
            this.msg = getArguments().getString("ATG_KEY_RES_MSG");
        }
    }

    @Nullable
    public View
    onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        View view = paramLayoutInflater.inflate(this.mLayoutResId, paramViewGroup, false);
        ((TextView) view.findViewById(R.id.cy_intro_tv)).setText(this.msg);
        return view;
    }
}

