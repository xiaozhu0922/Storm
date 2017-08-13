package csmz.storm.zhu.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import csmz.storm.zhu.duanzi.mode.DuanBean;

/**
 * 用于 Json 解析的帮助类
 */

public class GsonUtil {

    public static List<DuanBean> getDuanziBeanList(String response) {

        List<DuanBean> mDuanziBeanList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            String dataArrayStr = jsonObject.getJSONObject("data").getString("data");
            Type type = new TypeToken<List<DuanBean>>() {
            }.getType();
            Gson gson = new Gson();
            mDuanziBeanList = gson.fromJson(dataArrayStr, type);
            return mDuanziBeanList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mDuanziBeanList;
    }
}
