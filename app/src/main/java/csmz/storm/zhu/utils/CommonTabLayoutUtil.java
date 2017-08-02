package csmz.storm.zhu.utils;

import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;


public class CommonTabLayoutUtil {

    /**
     * 初始化CommonTabLayout 的标题和图表
     */
    public static ArrayList<CustomTabEntity> initTabTitles(final String[] titles, final int[] icons, final int[] selectedIcons) {
        ArrayList<CustomTabEntity> mTabTitles = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            final int index = i;
            mTabTitles.add(new CustomTabEntity() {

                @Override
                public int getTabUnselectedIcon() {
                    return icons[index];
                }

                @Override
                public String getTabTitle() {
                    return titles[index];
                }

                @Override
                public int getTabSelectedIcon() {
                    return selectedIcons[index];
                }
            });
        }
        return mTabTitles;
    }


    /**
     * 设置CommonTabLayout的Table选择的监听事件
     *
     * @param tl_main
     * @param viewPager
     */
    public static void setOnTabSelectListener(CommonTabLayout tl_main, final ViewPager viewPager) {
        tl_main.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
