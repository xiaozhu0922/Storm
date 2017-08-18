package csmz.storm.zhu.duanzi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import csmz.storm.zhu.R;
import csmz.storm.zhu.adapters.LinearLayoutColorDivider;
import csmz.storm.zhu.app.CommonFragment;
import csmz.storm.zhu.constants.Constants;
import csmz.storm.zhu.duanzi.mode.DuanBean;
import csmz.storm.zhu.duanzi.presenter.IDuanZiPresenter;
import csmz.storm.zhu.duanzi.presenter.IDuanZiPresenterComp;
import csmz.storm.zhu.duanzi.view.IDuanZiView;
import csmz.storm.zhu.presenter.IMultiClickPresenter;
import csmz.storm.zhu.presenter.MultiClickPresenterCompl;
import csmz.storm.zhu.ui.XRecyclerView;
import csmz.storm.zhu.utils.ClickEffectUtil;
import csmz.storm.zhu.view.IMultiClickView;


public class DuanZiFragment extends CommonFragment implements IDuanZiView, View.OnClickListener, IMultiClickView, XRecyclerView.IXRecyclerViewListener {
    private XRecyclerView mRecyclerDuan;
    // private SwipeRefreshLayout mRefresh;
    private TextView isEmpty;

    private IDuanZiPresenter mIDuanZiPresenter;
    private IMultiClickPresenter mIMultiClickPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.duanzi_fragment, container, false);

        initUI(view);
        setOnClickListener();
        mIDuanZiPresenter.getDuanZiData(getActivity(), Constants.URL.GET_DUANZI);
        return view;
    }

    @Override
    public void initUI(View view) {
        mIMultiClickPresenter = new MultiClickPresenterCompl(this);
        mIDuanZiPresenter = new IDuanZiPresenterComp(this);

        mRecyclerDuan = findView(R.id.recycler_duanzi, view);
        //  mRefresh = findView(R.id.duanzi_refresh, view);
        isEmpty = findView(R.id.tv_query_none, view);

//        mRefresh.setColorSchemeResources(R.color.colorPrimary);
//        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mIDuanZiPresenter.getDuanZiData(getActivity(), Constants.URL.GET_DUANZI);
//                mRefresh.setRefreshing(false);
//            }
//        });
    }

    @Override
    public void initData() {


    }

    @Override
    public void setOnClickListener() {
      //  ClickEffectUtil.set(mRecyclerDuan);
    }

    @Override
    public void dismissLoading() {
        dismissDialog();
    }

    @Override
    public void showLoading(String msg) {
        showDialog(msg);
    }

    @Override
    public void addDataBean(List<DuanBean> beanList) {
        mRecyclerDuan.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerDuan.addItemDecoration(new LinearLayoutColorDivider(getResources(), R.color.gray_black, R.dimen.line_height, LinearLayoutManager.VERTICAL));
        mRecyclerDuan.setRefreashEnable(true);
        mRecyclerDuan.setPullLoadEnable(true);
        beanList.remove(3);
        mRecyclerDuan.setAdapter(new DuanZiAdapter(DuanZiFragment.this, beanList));
        mRecyclerDuan.setXRecyclerViewListener(this);
        isEmpty.setVisibility(beanList.size() == 0 ? View.VISIBLE : View.GONE);

    }

    @Override
    public void onClick(View view) {
        mIMultiClickPresenter.onDealMultiClick(view);
    }

    @Override
    public void onMultiClickResult(View view) {

    }

    @Override
    public void onRefresh() {
        mIDuanZiPresenter.getDuanZiData(getActivity(), Constants.URL.GET_DUANZI);
        mRecyclerDuan.stopRefresh();
    }

    @Override
    public void onLoadMore() {
        mIDuanZiPresenter.getDuanZiData(getActivity(), Constants.URL.GET_DUANZI);
    }
}
