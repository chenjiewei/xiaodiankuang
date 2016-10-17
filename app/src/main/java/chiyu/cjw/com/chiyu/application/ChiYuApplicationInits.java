package chiyu.cjw.com.chiyu.application;

import com.cjw.chiyu.common.log.LogPoxy;
import com.cjw.chiyu.common.persistentdata.sharepreferencesutils.SpConfig;
import com.cjw.chiyu.common.persistentdata.sharepreferencesutils.SpManager;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:09
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class ChiYuApplicationInits {
    private static final String TAG = "ChiYuApplicationInits";

    public void initApplication() {
        if (!SpManager.getNewInstance().gainBooleanValue(SpConfig.IS_NEVER_ROOT)) {
            // 数据获取,获取向前推一个月的数据
            long currentTime = System.currentTimeMillis() / 1000L - 30 * 24 * 60 * 60;
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_WORD, (int) currentTime);
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_RANDOM, (int) currentTime);
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_GIF, (int) currentTime);
            SpManager.getNewInstance().saveInt(SpConfig.FRIST_INSTALL_TIME, (int) (System.currentTimeMillis() / 1000L));
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_WORD_PAGE, 1);
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_RANDOM_PAGE, 1);
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_GIF_PAGE, 1);
            SpManager.getNewInstance().saveBoolean(SpConfig.IS_NEVER_ROOT, true);
        }
        int last_datachange_time = SpManager.getNewInstance().gainIntValue(SpConfig.FRIST_INSTALL_TIME);
        int currentTime = (int) (System.currentTimeMillis() / 1000L);
        // 超过了两天数据戳改变,页数改变
        if (currentTime - last_datachange_time >= 48 * 60 * 60) {
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_WORD_PAGE, 1);
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_RANDOM_PAGE, 1);
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_GIF_PAGE, 1);
            SpManager.getNewInstance().saveInt(SpConfig.FRIST_INSTALL_TIME, currentTime);
        }
    }
}
