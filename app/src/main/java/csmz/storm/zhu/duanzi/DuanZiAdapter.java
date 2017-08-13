package csmz.storm.zhu.duanzi;

import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import csmz.storm.zhu.R;
import csmz.storm.zhu.duanzi.mode.DuanBean;
import de.hdodenhof.circleimageview.CircleImageView;

public class DuanZiAdapter extends RecyclerView.Adapter<DuanZiAdapter.DuanziViewHolder> {
    private Fragment mFragment;
    private List<DuanBean> mDuanziBeanList;

    public DuanZiAdapter(Fragment mFragment, List<DuanBean> mDuanziBeanList) {
        this.mFragment = mFragment;
        this.mDuanziBeanList = mDuanziBeanList;
    }

    @Override
    public DuanziViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_duanzi, null);
        return new DuanziViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DuanziViewHolder holder, int position) {
        try {
            DuanBean duanBean = mDuanziBeanList.get(position);
            Glide.with(mFragment).load(duanBean.getGroupBean().getUser().getAvatar_url()).into(holder.mCiv);
            holder.mTvTitle.setText(duanBean.getGroupBean().getUser().getName());
            holder.mTvContent.setText(duanBean.getGroupBean().getText());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return mDuanziBeanList.size();
    }

    public static class DuanziViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mCiv;
        private TextView mTvTitle;
        private TextView mTvContent;

        public DuanziViewHolder(View itemView) {
            super(itemView);
            mCiv = itemView.findViewById(R.id.civ_icon);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mTvContent = itemView.findViewById(R.id.tv_content);
        }


    }


}
