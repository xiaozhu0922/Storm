package csmz.storm.zhu.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import csmz.storm.zhu.R;


public class XRecyclerViewFooter extends LinearLayout {
    public final static int STATE_NORMAL = 0;

    public final static int STATE_READY = 1;

    public final static int STATE_LOADING = 2;

    private Context mContext;

    private View mContentView;

    private View mProgressBar;

    private TextView mHintView;

    private View mProgressView;

    public XRecyclerViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public XRecyclerViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void setState(int state) {
        mHintView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mProgressView.setVisibility(View.INVISIBLE);
        mHintView.setVisibility(View.INVISIBLE);
        if (state == STATE_READY) {
            mHintView.setVisibility(View.VISIBLE);
            mHintView.setText(R.string.xlistview_footer_hint_ready);
        } else if (state == STATE_LOADING) {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressView.setVisibility(View.VISIBLE);
        } else {
            mHintView.setVisibility(View.VISIBLE);
            mHintView.setText(R.string.xlistview_footer_hint_normal);
        }
    }

    public void setBottomMargin(int height) {
        if (height < 0)
            return;
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.bottomMargin = height;
        mContentView.setLayoutParams(lp);
    }

    public int getBottomMargin() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        return lp.bottomMargin;
    }

    /**
     * normal status
     */
    public void normal() {
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mProgressView.setVisibility(View.GONE);
    }

    /**
     * loading status
     */
    public void loading() {
        mHintView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressView.setVisibility(View.INVISIBLE);
    }

    /**
     * hide footer when disable pull load more
     */
    public void hide() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.height = 0;
        mContentView.setLayoutParams(lp);
    }

    /**
     * show footer
     */
    public void show() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.height = LayoutParams.WRAP_CONTENT;
        mContentView.setLayoutParams(lp);
    }

    private void initView(Context context) {
        mContext = context;
        LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.xlistview_footer, null);
        addView(moreView);
        moreView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

        mContentView = moreView.findViewById(R.id.xlistview_footer_content);
        mProgressBar = moreView.findViewById(R.id.xlistview_footer_progressbar);
        mProgressView = moreView.findViewById(R.id.xlistview_footer_loading_view);
        mHintView = (TextView) moreView.findViewById(R.id.xlistview_footer_hint_textview);
    }

}
