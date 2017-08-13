package csmz.storm.zhu.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2017/8/12.
 */

public class ApiClient {
    /**
     * 用于发送 Get 请求的封装方法
     *
     * @param context  Activity 的实例
     * @param url      请求的地址
     * @param callback 用于网络回调的接口
     */
    public static void sendHttpGet(Context context, String url, final VolleyListener callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                callback.onSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });
        requestQueue.add(stringRequest);

    }

}
