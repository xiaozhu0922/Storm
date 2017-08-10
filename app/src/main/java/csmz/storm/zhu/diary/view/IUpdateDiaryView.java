package csmz.storm.zhu.diary.view;

import android.content.Intent;

/**
 * Created by uniface on 2017/8/10.
 */

public interface IUpdateDiaryView {

    /**
     * 初始化UI
     */
    void initUI();

    /**
     * 初始化数据
     */
    void initData(Intent intent);

    /**
     * 设置点击事件
     */
    void setClickListener();
}
