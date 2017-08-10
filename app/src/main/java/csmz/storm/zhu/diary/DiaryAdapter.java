package csmz.storm.zhu.diary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import csmz.storm.zhu.R;
import csmz.storm.zhu.diary.event.StartUpdateDiaryEvent;
import csmz.storm.zhu.diary.mode.DiaryBean;
import csmz.storm.zhu.utils.GetDateUtils;

/**
 * Created by uniface on 2017/8/4.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder> {

    private Context mContext;
    private List<DiaryBean> mData;
    private LayoutInflater mLayoutInflater;
    private int mEditPosition = -1;

    public DiaryAdapter(Context context, List<DiaryBean> diaryBeen) {
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = diaryBeen;
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiaryViewHolder(mLayoutInflater.inflate(R.layout.item_diary, parent, false));
    }

    @Override
    public void onBindViewHolder(final DiaryViewHolder holder, final int position) {
        String dateSystem = GetDateUtils.getDate().toString();
        if (mData.get(position).getDate().equals(dateSystem)) {
            holder.mIvCircle.setImageResource(R.drawable.yuan);
        }
        holder.mTvDate.setText(mData.get(position).getDate());
        holder.mTvTitle.setText(mData.get(position).getTitle());
        holder.mTvContent.setText("       " + mData.get(position).getContent());
        holder.mIvEdit.setVisibility(View.INVISIBLE);
        if (mEditPosition == position) {
            holder.mIvEdit.setVisibility(View.VISIBLE);
        } else {
            holder.mIvEdit.setVisibility(View.GONE);
        }
        holder.mLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.mIvEdit.getVisibility() == View.VISIBLE) {
                    holder.mIvEdit.setVisibility(View.GONE);
                } else {
                    holder.mIvEdit.setVisibility(View.VISIBLE);
                }
                if (mEditPosition != position) {
                    notifyItemChanged(mEditPosition);
                }
                mEditPosition = position;
            }
        });

        holder.mIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().post(new StartUpdateDiaryEvent(mData.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class DiaryViewHolder extends RecyclerView.ViewHolder {

        TextView mTvDate;
        TextView mTvTitle;
        TextView mTvContent;
        ImageView mIvEdit;
        LinearLayout mLlTitle;
        LinearLayout mLl;
        ImageView mIvCircle;
        LinearLayout mLlControl;
        RelativeLayout mRlEdit;

        DiaryViewHolder(View view) {
            super(view);

            mIvCircle = (ImageView) view.findViewById(R.id.main_iv_circle);
            mTvDate = (TextView) view.findViewById(R.id.main_tv_date);
            mTvTitle = (TextView) view.findViewById(R.id.main_tv_title);
            mTvContent = (TextView) view.findViewById(R.id.main_tv_content);
            mIvEdit = (ImageView) view.findViewById(R.id.main_iv_edit);
            mLlTitle = (LinearLayout) view.findViewById(R.id.main_ll_title);
            mLl = (LinearLayout) view.findViewById(R.id.item_ll);
            mLlControl = (LinearLayout) view.findViewById(R.id.item_ll_control);
            mRlEdit = (RelativeLayout) view.findViewById(R.id.item_rl_edit);
        }
    }


}
