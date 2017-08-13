package csmz.storm.zhu.utils;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2017/8/12.
 */

public interface VolleyListener {
    void onSuccess(String response);

    void onError(VolleyError error);
}
