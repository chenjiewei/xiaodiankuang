package chiyu.cjw.com.chiyu.manager.utils;

import com.cjw.chiyu.common.persistentdata.sharepreferencesutils.SpConfig;
import com.cjw.chiyu.common.persistentdata.sharepreferencesutils.SpManager;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:26
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class jokeObtainDataUtils {
    public static int getJokePicPage() {
        int i = SpManager.getNewInstance().gainIntValue(SpConfig.OBATAIN_DATA_TIME_KEY_GIF_PAGE);
        if (i != -1) {
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_WORD_PAGE, i + 1);
        }
        return i;
    }

    public static String getJokePicTime() {
        int i = SpManager.getNewInstance().gainIntValue(SpConfig.OBATAIN_DATA_TIME_KEY_WORD);
        if (i != -1) {
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_WORD, i + 60);
        }
        return String.valueOf(i);
    }

    public static int getJokeRandomPage() {
        int i = SpManager.getNewInstance().gainIntValue(SpConfig.OBATAIN_DATA_TIME_KEY_RANDOM_PAGE);
        if (i != -1) {
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_WORD_PAGE, i + 1);
        }
        return i;
    }

    public static String getJokeRandomTime() {
        int i = SpManager.getNewInstance().gainIntValue(SpConfig.OBATAIN_DATA_TIME_KEY_WORD);
        if (i != -1) {
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_WORD, i + 60);
        }
        return String.valueOf(i);
    }

    public static int getJokeWordSPage() {
        int i = SpManager.getNewInstance().gainIntValue(SpConfig.OBATAIN_DATA_TIME_KEY_WORD_PAGE);
        if (i != -1) {
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_WORD_PAGE, i + 1);
        }
        return i;
    }

    public static String getJokeWordSTime() {
        int i = SpManager.getNewInstance().gainIntValue(SpConfig.OBATAIN_DATA_TIME_KEY_WORD);
        if (i != -1) {
            SpManager.getNewInstance().saveInt(SpConfig.OBATAIN_DATA_TIME_KEY_WORD, i + 60);
        }
        return String.valueOf(i);
    }
}
