package csmz.storm.zhu.diary.view;

import android.view.View;

/**
 * Created by uniface on 2017/8/4.
 */

public interface IDiaryView {
    /**
     * 初始化UI
     */
    void initUI(View view);

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 设置点击事件
     */
    void setClickListener();
}
