package chiyu.cjw.com.chiyu.manager.model.homeuimodel;

/**
 * Created: chnejiewei
 * data: 2016/9/10 12:21
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class HomeUIJokeModel {
    public int againstNum;
    public int aggreeNum;
    public int commentNum;
    public String content;
    public String contentTitle;
    public boolean isHadGodComment;
    public String jokeId;
    public JOKETYPE mType;
    public String origialPlace;
    public int shareNum;
    public String userHeadPic;
    public String userNick;

    public HomeUIJokeModel() {
    }

    public HomeUIJokeModel(int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, boolean paramBoolean, String paramString3, JOKETYPE paramJOKETYPE, String paramString4, int paramInt4, String paramString5, String paramString6) {
        this.againstNum = paramInt1;
        this.aggreeNum = paramInt2;
        this.commentNum = paramInt3;
        this.content = paramString1;
        this.contentTitle = paramString2;
        this.isHadGodComment = paramBoolean;
        this.jokeId = paramString3;
        this.mType = paramJOKETYPE;
        this.origialPlace = paramString4;
        this.shareNum = paramInt4;
        this.userHeadPic = paramString5;
        this.userNick = paramString6;
    }

    public enum JOKETYPE {
        WORDS, PICTURE, GIF;
    }
}
