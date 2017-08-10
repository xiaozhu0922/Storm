package csmz.storm.zhu.diary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import csmz.storm.zhu.R;
import csmz.storm.zhu.app.CommonActivity;
import csmz.storm.zhu.diary.view.IUpdateDiaryView;
import csmz.storm.zhu.utils.ClickEffectUtil;
import csmz.storm.zhu.utils.GetDateUtils;

public class UpDateDiaryActivity extends CommonActivity implements IUpdateDiaryView {
    private TextView tvTitle, tvTime;
    private Toolbar toolbar;

    public static void startActivity(Context context, String title, String content, String tag) {
        Intent intent = new Intent(context, UpDateDiaryActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_data_diary);
        initUI();
        setClickListener();
    }

    @Override
    public void initUI() {
        toolbar = findView(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.app_back);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tvTitle = findView(R.id.toolbar_title);
        tvTitle.setText("修改日记");
        tvTime = findView(R.id.add_time);
        tvTime.setText("今天," + GetDateUtils.getDate());
    }

    @Override
    public void initData() {

    }

    @Override
    public void setClickListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                backDiaryFragment();
            }

        });
        ClickEffectUtil.set(toolbar);
    }

    private void backDiaryFragment() {


    }
}
