package csmz.storm.zhu.presenter;

import android.view.View;

import csmz.storm.zhu.view.IMultiClickView;


public class MultiClickPresenterCompl implements IMultiClickPresenter {
    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;

    private IMultiClickView iMultiClickView;

    public MultiClickPresenterCompl(IMultiClickView iMultiClickView) {
        this.iMultiClickView = iMultiClickView;
    }

    @Override
    public void onDealMultiClick(View view) {
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            lastClickTime = curClickTime;
            iMultiClickView.onMultiClickResult(view);
        }
    }
}
