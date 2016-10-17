package chiyu.cjw.com.chiyu.ui.welcomeui.introui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.cjw.chiyu.common.persistentdata.sharepreferencesutils.SpConfig;
import com.cjw.chiyu.common.persistentdata.sharepreferencesutils.SpManager;
import com.github.paolorotolo.appintro.AppIntro;

import org.greenrobot.eventbus.EventBus;

import chiyu.cjw.com.chiyu.R;
import chiyu.cjw.com.chiyu.ui.eventbus.EventBusBean;
import chiyu.cjw.com.chiyu.ui.eventbus.EventBusConstant;
import chiyu.cjw.com.chiyu.ui.uiconfig.UiConfigConstant;
import chiyu.cjw.com.chiyu.ui.uiutils.ToastUilts;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:23
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class IntroUi extends AppIntro {
    public static final long MAX_DOUBLE_BACK_DURATION = 1500L;
    private final String[] cy_intro_msg = {"如果你很忙，除了你真的很重要意外，更可能的原因是你很弱!", "社会精英阶层一边啃着心灵鸡腿和心灵鸡胸，一边为我们送上了心灵鸡汤，说人生的价值并不在于你挣了多少钱和外在是否美!", "直到三十岁才知道，和不同的人说不同的话，表现出不一样的态度，是一种非常可贵的能力，而不是虚伪!", "你这么努力，忍受那么多寂寞和纠结，我们也没觉得你有多优秀!", "人生就是这样，有欢笑也有泪水。一部分人主要负责欢笑，另一部分人主要负责泪水!"};
    private long lastBackKeyDownTick = 0L;

    public void init(Bundle paramBundle) {
        addSlide(IntroFragment.getInstances(R.layout.cy_intro_page_one_fragment, this.cy_intro_msg[0]));
        addSlide(IntroFragment.getInstances(R.layout.cy_intro_page_two_fragment, this.cy_intro_msg[1]));
        addSlide(IntroFragment.getInstances(R.layout.cy_intro_page_three_fragment, this.cy_intro_msg[2]));
        addSlide(IntroFragment.getInstances(R.layout.cy_intro_page_four_fragment, this.cy_intro_msg[3]));
        addSlide(IntroFragment.getInstances(R.layout.cy_intro_page_five_fragment, this.cy_intro_msg[4]));
        ((Button) this.skipButton).setText(UiConfigConstant.INTRO_UI_SKIN_TEXT);
        ((Button) this.doneButton).setText(UiConfigConstant.INTRO_UI_STRAT_APP_TEXT);
        setProgressIndicator();
        setIndicatorColor(R.color.button_material_dark, R.color.colorAccent);
    }

    @Override
    public void onBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.lastBackKeyDownTick > MAX_DOUBLE_BACK_DURATION) {
            ToastUilts.showMessage(this, UiConfigConstant.EXIT_APP);
            this.lastBackKeyDownTick = currentTimeMillis;
            return;
        }
        finish();
        EventBus.getDefault().post(new EventBusBean(EventBusConstant.EVENT_BUS_CLOSE_ACTIVITY));
    }

    public void onDonePressed() {
        SpManager.getNewInstance().saveBoolean(SpConfig.IS_NEVER_ROOT, true);
        finish();
    }

    public void onNextPressed() {
    }

    public void onSkipPressed() {
        SpManager.getNewInstance().saveBoolean(SpConfig.IS_NEVER_ROOT, true);
        finish();
    }

    public void onSlideChanged() {
    }
}

