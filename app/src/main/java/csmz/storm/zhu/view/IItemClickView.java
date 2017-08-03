package csmz.storm.zhu.view;

import android.view.View;

/**
 * Created by wm_02 on 2017/5/27.
 */

public interface IItemClickView {
    /**
     * Item的点击
     */
    void onItemClick(View view, int position, int type);

    /**
     * Item的长点击
     */
    void onItemLongClick(View view, int position, int type);
}
