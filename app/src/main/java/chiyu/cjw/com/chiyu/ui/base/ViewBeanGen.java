package chiyu.cjw.com.chiyu.ui.base;

/**
 * Created: chnejiewei
 * data: 2016/9/10 20:02
 * blog: http://blog.csdn.net/u010392352
 * weibo：C-Xstart
 * description:
 */
public class ViewBeanGen<T> {
    private T value;

    public ViewBeanGen(T paramT) {
        this.value = paramT;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T paramT) {
        this.value = paramT;
    }
}
