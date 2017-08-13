package csmz.storm.zhu.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import csmz.storm.zhu.R;
import csmz.storm.zhu.utils.ClickEffectUtil;


/**
 * Created by wm_02 on 2017/8/8.
 */
public class XRecyclerView extends RecyclerView {
    private final static int SCROLLBACK_HEADER = 0;
    private final static int SCROLLBACK_FOOTER = 1;
    // 滑动时长
    private final static int SCROLL_DURATION = 400;
    // 加载更多的距离
    private final static int PULL_LOAD_MORE_DELTA = 100;
    // 滑动比例
    private final static float OFFSET_RADIO = 2f;
    // 记录按下点的y坐标
    private float lastY;
    // 用来回滚
    private Scroller scroller;
    private IXRecyclerViewListener mXRecyclerViewListener;
    private XRecyclerViewHeader headerView;
    private RelativeLayout headerViewContent;
    // header的高度
    private int headerHeight;
    // 是否能够刷新
    private boolean enableRefresh = false;
    // 是否正在刷新
    private boolean isRefreashing = false;
    // footer
    private XRecyclerViewFooter footerView;
    //是否有触发点击事件(包括滑动)
    private boolean isEvent = false;
    // 是否可以加载更多
    private boolean enableLoadMore;
    // 是否正在加载
    private boolean isLoadingMore;
    // 是否footer准备状态
    private boolean isFooterAdd = false;
    // 记录是从header还是footer返回
    private int mScrollBack;

    private Adapter mWrapAdapter;//组合的Adapter

    public XRecyclerView(Context context) {
        super(context);
        initView(context);
    }

    public XRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {

        scroller = new Scroller(context, new DecelerateInterpolator());

        headerView = new XRecyclerViewHeader(context);
        footerView = new XRecyclerViewFooter(context);

        headerViewContent = (RelativeLayout) headerView.findViewById(R.id.xlistview_header_content);
        headerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public void onGlobalLayout() {
                        headerHeight = headerViewContent.getHeight();
                        getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                    }
                });
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mWrapAdapter = new WrapAdapter(this, headerView, footerView, adapter);
        super.setAdapter(mWrapAdapter);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
        int totalItemCount = getLayoutManager().getItemCount();
        //如果列表条数为零(放弃头尾两个item),则不处理
        if (totalItemCount == 2) {
            return false;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isEvent = true;
                // 记录按下的坐标
                lastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算移动距离
                float deltaY = ev.getRawY() - lastY;
                //这里的处理是为了防止与item的点击事件其冲突
                if (!isEvent) {
                    isEvent = true;
                    deltaY = 0;
                }
                lastY = ev.getRawY();
                //有一个在加载数据的时候,另外一个不加载数据
                if (!isRefreashing && !isLoadingMore) {
                    // 是第一项并且标题已经显示或者是在下拉
                    if (layoutManager.findFirstCompletelyVisibleItemPosition() == 0 && (headerView.getVisiableHeight() > 0 || deltaY > 0) && enableRefresh) {
                        updateHeaderHeight(deltaY / OFFSET_RADIO);
                    } else if (layoutManager.findLastCompletelyVisibleItemPosition() == totalItemCount - 1 && (footerView.getBottomMargin() > 0 || deltaY < 0) && enableLoadMore) {
                        updateFooterHeight(-deltaY / OFFSET_RADIO);
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                isEvent = false;
                if (layoutManager.findFirstVisibleItemPosition() == 0) {
                    if (enableRefresh && headerView.getVisiableHeight() > headerHeight) {
                        isRefreashing = true;
                        headerView.setState(XRecyclerViewHeader.STATE_REFRESHING);
                        if (mXRecyclerViewListener != null) {
                            mXRecyclerViewListener.onRefresh();
                        }
                    }
                    resetHeaderHeight();
                } else if (layoutManager.findLastVisibleItemPosition() == totalItemCount - 1) {
                    if (enableLoadMore && footerView.getBottomMargin() > PULL_LOAD_MORE_DELTA) {
                        startLoadMore();
                    }
                    resetFooterHeight();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        // 松手之后调用
        if (scroller.computeScrollOffset()) {

            if (mScrollBack == SCROLLBACK_HEADER) {
                headerView.setVisiableHeight(scroller.getCurrY());
            } else {
                footerView.setBottomMargin(scroller.getCurrY());
            }
            postInvalidate();
        }
        super.computeScroll();
    }

    /**
     * 设置刷新标志位
     *
     * @param enable
     */
    public void setRefreashEnable(boolean enable) {
        this.enableRefresh = enable;
    }

    public void setPullLoadEnable(boolean enable) {
        enableLoadMore = enable;
        if (!enableLoadMore) {
            footerView.hide();
            footerView.setOnClickListener(null);
        } else {
            isLoadingMore = false;
            footerView.show();
            footerView.setState(XRecyclerViewFooter.STATE_NORMAL);
            TextView tv_footer = (TextView) footerView.findViewById(R.id.xlistview_footer_hint_textview);
            //ClickEffectUtil.set(tv_footer);
            tv_footer.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //有一个在加载数据的时候,另外一个不加载数据
                    if (!isRefreashing && !isLoadingMore) {
                        startLoadMore();
                    }
                }
            });
        }
    }

    public void stopRefresh() {
        if (isRefreashing == true) {
            isRefreashing = false;
            resetHeaderHeight();
        }
    }

    public void stopLoadMore() {
        if (isLoadingMore == true) {
            isLoadingMore = false;
            footerView.setState(XRecyclerViewFooter.STATE_NORMAL);
        }
    }

    private void updateHeaderHeight(float delta) {
        headerView.setVisiableHeight((int) delta + headerView.getVisiableHeight());
        // 未处于刷新状态，更新箭头
        if (enableRefresh && !isRefreashing) {
            if (headerView.getVisiableHeight() > headerHeight) {
                headerView.setState(XRecyclerViewHeader.STATE_READY);
            } else {
                headerView.setState(XRecyclerViewHeader.STATE_NORMAL);
            }
        }
    }

    private void resetHeaderHeight() {
        // 当前的可见高度
        int height = headerView.getVisiableHeight();
        // 如果正在刷新并且高度没有完全展示
        if ((isRefreashing && height <= headerHeight) || (height == 0)) {
            return;
        }
        // 默认会回滚到header的位置
        int finalHeight = 0;
        // 如果是正在刷新状态，则回滚到header的高度
        if (isRefreashing && height > headerHeight) {
            finalHeight = headerHeight;
        }
        mScrollBack = SCROLLBACK_HEADER;
        // 回滚到指定位置
        scroller.startScroll(0, height, 0, finalHeight - height,
                SCROLL_DURATION);
        // 触发computeScroll
        invalidate();
    }

    private void updateFooterHeight(float delta) {
        int height = footerView.getBottomMargin() + (int) delta;
        if (enableLoadMore && !isLoadingMore) {
            if (height > PULL_LOAD_MORE_DELTA) {
                footerView.setState(XRecyclerViewFooter.STATE_READY);
            } else {
                footerView.setState(XRecyclerViewFooter.STATE_NORMAL);
            }
        }
        footerView.setBottomMargin(height);
    }

    private void resetFooterHeight() {
        int bottomMargin = footerView.getBottomMargin();
        if (bottomMargin > 0) {
            mScrollBack = SCROLLBACK_FOOTER;
            scroller.startScroll(0, bottomMargin, 0, -bottomMargin, SCROLL_DURATION);
            invalidate();
        }
    }

    private void startLoadMore() {
        isLoadingMore = true;
        footerView.setState(XRecyclerViewFooter.STATE_LOADING);
        if (mXRecyclerViewListener != null) {
            mXRecyclerViewListener.onLoadMore();
        }
    }

    public boolean isEnableLoadMore() {
        return enableLoadMore;
    }

    public void setXRecyclerViewListener(IXRecyclerViewListener mXRecyclerViewListener) {
        this.mXRecyclerViewListener = mXRecyclerViewListener;
    }

    public interface IXRecyclerViewListener {

        void onRefresh();

        void onLoadMore();
    }
}
