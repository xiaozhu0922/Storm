package csmz.storm.zhu.duanzi.view;

import android.view.View;

/**
 * Created by uniface on 2017/8/3.
 */

public interface IDuanZiView {

    /**
     * 初始化UI
     */
    void initUI(View view);

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 设置点击监听
     */
    void setOnClickListener();

}
