package csmz.storm.zhu.duanzi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import csmz.storm.zhu.R;
import csmz.storm.zhu.app.CommonFragment;
import csmz.storm.zhu.duanzi.view.IDuanZiView;
import csmz.storm.zhu.presenter.IMultiClickPresenter;
import csmz.storm.zhu.presenter.MultiClickPresenterCompl;
import csmz.storm.zhu.utils.ClickEffectUtil;
import csmz.storm.zhu.utils.OkHttpUtils;
import csmz.storm.zhu.utils.ToastUtil;
import csmz.storm.zhu.view.IMultiClickView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class DuanZiFragment extends CommonFragment implements IDuanZiView, View.OnClickListener, IMultiClickView {
    private Button button;
    private TextView textView;

    private IMultiClickPresenter mIMultiClickPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.duanzi_fragment, container, false);
        initUI(view);
        setOnClickListener();

        return view;
    }

    @Override
    public void initUI(View view) {
        mIMultiClickPresenter = new MultiClickPresenterCompl(this);

        button = findView(R.id.btn_send, view);
        textView = findView(R.id.tv_text, view);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setOnClickListener() {
        ClickEffectUtil.set(button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mIMultiClickPresenter.onDealMultiClick(view);
    }

    @Override
    public void onMultiClickResult(View view) {


        switch (view.getId()) {
            case R.id.btn_send:

                break;

        }
    }
}
