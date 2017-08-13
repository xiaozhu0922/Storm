//package csmz.storm.zhu.utils;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//
///**
// * Created by uniface on 2017/8/3.
// */
//
//public class OkHttpUtils {
//    /**
//     * get请求
//     *
//     * @param address
//     * @param callback
//     */
//    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(address)
//                .build();
//        client.newCall(request).enqueue(callback);
//    }
//
//}
