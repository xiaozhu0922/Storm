package csmz.storm.zhu.utils;

import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;


public class CommonUtil {
    /**
     * 获取资源文件对应的图片id
     *
     * @param ar
     * @return
     */
    public static int[] getArrayId(TypedArray ar) {
        int len = ar.length();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++)
            resIds[i] = ar.getResourceId(i, 0);
        ar.recycle();
        return resIds;
    }

    ;

    public static void addOnPageChangeListener(ViewPager viewPager, final CommonInterfaceUtil.ViewPageSelectedListener viewPageSelectedListener) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPageSelectedListener.onSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
