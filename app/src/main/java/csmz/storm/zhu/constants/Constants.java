package csmz.storm.zhu.constants;


import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import csmz.storm.zhu.utils.GetRandom;
import csmz.storm.zhu.utils.ToastUtil;

public class Constants {

    public static final String DB_NAME = "Diary.db";

    private static String meiziData = "";

    /**
     * 返回一个随机生成的妹子 Api
     *
     * @return meizi Api
     */
    public static String getMeiziApi() {
        StringBuilder meiziApi = new StringBuilder();
        meiziApi.append("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/").append("15").append("/" + GetRandom.getRandom());
        return String.valueOf(meiziApi);
    }

    public static String getMeiziData(final Context context) {
        String url = getMeiziApi();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                meiziData = s;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showShort(context, error.getMessage());
            }
        });
        //SleepApplication sleepApplication = new SleepApplication();
        requestQueue.add(stringRequest);
        return meiziData;
    }

    public static class URL {
        /**
         * 获取段子数据
         */
        public static final String GET_DUANZI = "http://is.snssdk.com/neihan/stream/mix/v1/?mpic=1&webp=1&essence=1&content_type=-102&message_cursor=-1&am_longitude=110&am_latitude=120&am_city=%E5%8C%97%E4%BA%AC%E5%B8%82&am_loc_time=1489226058493&count=30&min_time=1489205901&screen_width=1450&do00le_col_mode=0&iid=3216590132&device_id=32613520945&ac=wifi&channel=360&aid=7&app_name=joke_essay&version_code=612&version_name=6.1.2&device_platform=android&ssmix=a&device_type=sansung&device_brand=xiaomi&os_api=28&os_version=6.10.1&uuid=326135942187625&openudid=3dg6s95rhg2a3dg5&manifest_version_code=612&resolution=1450*2800&dpi=620&update_version_code=6120";


    }
}
