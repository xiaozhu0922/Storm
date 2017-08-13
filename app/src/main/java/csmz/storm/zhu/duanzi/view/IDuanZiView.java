package csmz.storm.zhu.duanzi.view;

import android.view.View;

import java.util.List;

import csmz.storm.zhu.duanzi.mode.DuanBean;

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

    /**
     * 取消进度条
     */
    void dismissLoading();

    /**
     * 显示进度条
     */
    void showLoading(String msg);


    void addDataBean(List<DuanBean> beanList);
}
