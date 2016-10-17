package chiyu.cjw.com.chiyu.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import chiyu.cjw.com.chiyu.ui.eventbus.EventBusBean;

/**
 * Created: chnejiewei
 * data: 2016/9/10 19:57
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    // activity跳转的数据载体
    protected Bundle mBundle = null;
    // 上下文
    protected Context mContext = null;
    // view的实体类
    protected BaseViewBean mIViewBean = null;
    // present对象
    protected T mPresenter = null;
    // 泛型对象
    protected ViewBeanGen mViewBeanGen = null;

    // 返回键的处理
    protected void backViewEvent() {
        if ((this.mIViewBean == null) || (this.mIViewBean.getBackView() == null)) {
            return;
        }
        this.mIViewBean.getBackView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.this.finish();
            }
        });
    }

    // eventBus方法处理
    protected abstract void eventBusThread(EventBusBean paramEventBusBean);

    // 逻辑处理的对象实例化
    protected abstract void initLogicObject();

    // 初始化present对象
    protected abstract void initPresenter();

    // 初始化view对象实体
    protected abstract BaseViewBean initViewBean();

    // 初始化导航栏
    protected abstract void inittoolBar();

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        this.mContext = this;
        this.mBundle = getIntent().getExtras();
        setContentView(setLayoutResouceID());
        initLogicObject();
        this.mIViewBean = initViewBean();
        if (this.mIViewBean != null) {
            this.mViewBeanGen = this.mIViewBean.gainViewBeanGen();
        }
        backViewEvent();
        inittoolBar();
        initPresenter();
        this.mPresenter.attach((V) this);
        EventBus.getDefault().register(this);
    }

    protected void onDestroy() {
        this.mPresenter.dettach();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * 接受获取事件总线
     *
     * @param paramEventBusBean
     */
    @Subscribe
    public void onEventMainThread(EventBusBean paramEventBusBean) {
        eventBusThread(paramEventBusBean);
    }

    protected void onResume() {
        super.onResume();
    }

    /**
     * 发送事件总线
     *
     * @param paramString
     */
    protected void sendEventBusMsg(String paramString) {
        EventBus.getDefault().post(new EventBusBean(paramString));
    }

    /**
     * 布局设置
     *
     * @return
     */
    protected abstract int setLayoutResouceID();

    /**
     * 带数据的跳转
     *
     * @param paramClass
     * @param paramBundle
     */
    public void startActivityWithExtras(Class<?> paramClass, Bundle paramBundle) {
        Intent localIntent = new Intent(this, paramClass);
        localIntent.putExtras(paramBundle);
        startActivity(localIntent);
    }

    /**
     * 不带数据的跳转
     *
     * @param paramClass
     */
    public void startActivityWithOutExtras(Class<?> paramClass) {
        startActivity(new Intent(this, paramClass));
    }
}

