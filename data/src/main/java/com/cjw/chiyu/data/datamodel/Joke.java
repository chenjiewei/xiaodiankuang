package com.cjw.chiyu.data.datamodel;

/**
 * Created: chnejiewei
 * data: 2016/9/10 11:54
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class Joke extends BaseModel {
    public final static int JOKE_TYPE_WORDS = 1;
    public final static int JOKE_TYPE_PICS = 2;
    public final static int JOKE_TYPE_GIFS = 3;

    private String mJoke_content;
    private String mJoke_ct;
    private String mJoke_id;
    private String mJoke_title;
    // 1：文字，2：图片：3：gif
    private int mJoke_type;

    public Joke(String id, String title, int type, String content, String ct) {
        super.initGerModel(new GerModel(this));
        this.mJoke_id = id;
        this.mJoke_title = title;
        this.mJoke_type = type;
        this.mJoke_content = content;
        this.mJoke_ct = ct;
    }

    public String getmJoke_content() {
        return this.mJoke_content;
    }

    public String getmJoke_ct() {
        return this.mJoke_ct;
    }

    public String getmJoke_id() {
        return this.mJoke_id;
    }

    public String getmJoke_title() {
        return this.mJoke_title;
    }

    public int getmJoke_type() {
        return this.mJoke_type;
    }

    public void setmJoke_content(String content) {
        this.mJoke_content = content;
    }

    public void setmJoke_ct(String ct) {
        this.mJoke_ct = ct;
    }

    public void setmJoke_id(String id) {
        this.mJoke_id = id;
    }

    public void setmJoke_title(String title) {
        this.mJoke_title = title;
    }

    public void setmJoke_type(int type) {
        this.mJoke_type = type;
    }
}
