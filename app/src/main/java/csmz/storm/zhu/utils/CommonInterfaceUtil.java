package csmz.storm.zhu.utils;


public class CommonInterfaceUtil {
    /**
     * 在主页面滑动ViewPage,页面位置回调
     */
    public interface PageChangeListener {
        void onChange(String title);
    }

    /**
     * 消息数目变更事件回调
     */
    public interface MsgNumUpdateListener {
        void onUpdate(int type, int num);
    }

    /**
     * 警告标志位的监听回调
     */
    public interface MainStateChangListener {
        void onStateChang(int state);
    }

    /**
     * Viewpage选择回调
     */
    public interface ViewPageSelectedListener {
        void onSelect(int position);
    }

    /**
     * Viewpage选择回调
     */
    public interface ArrawClickListener {
        void onClick(int position);
    }
}
