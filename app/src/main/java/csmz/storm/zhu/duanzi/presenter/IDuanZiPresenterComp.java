package csmz.storm.zhu.duanzi.presenter;

import android.content.Context;

import com.android.volley.VolleyError;

import java.util.List;

import csmz.storm.zhu.constants.Constants;
import csmz.storm.zhu.duanzi.mode.DuanBean;
import csmz.storm.zhu.duanzi.presenter.IDuanZiPresenter;
import csmz.storm.zhu.duanzi.view.IDuanZiView;
import csmz.storm.zhu.utils.ApiClient;
import csmz.storm.zhu.utils.GsonUtil;
import csmz.storm.zhu.utils.ToastUtil;
import csmz.storm.zhu.utils.VolleyListener;

/**
 * Created by Administrator on 2017/8/12.
 */

public class IDuanZiPresenterComp implements IDuanZiPresenter {

    private IDuanZiView mIDuanZiView;

    public IDuanZiPresenterComp(IDuanZiView mIDuanZiView) {
        this.mIDuanZiView = mIDuanZiView;
    }

    @Override
    public void getDuanZiData(final Context context, String url) {

        mIDuanZiView.showLoading("正在获取数据...");

        ApiClient.sendHttpGet(context, url, new VolleyListener() {
            @Override
            public void onSuccess(String response) {
                mIDuanZiView.dismissLoading();
                if (response != null) {
                    List<DuanBean> mDuanBeanList = GsonUtil.getDuanziBeanList(response);

                    if (mDuanBeanList != null) {
                        mIDuanZiView.addDataBean(mDuanBeanList);
                    }

                } else {
                    ToastUtil.showShort(context, "数据错误");
                }

            }

            @Override
            public void onError(VolleyError error) {
                ToastUtil.showShort(context, error.getMessage());
            }
        });
    }
}
