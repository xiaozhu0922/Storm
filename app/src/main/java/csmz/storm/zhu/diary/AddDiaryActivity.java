package csmz.storm.zhu.diary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import csmz.storm.zhu.R;
import csmz.storm.zhu.app.CommonActivity;
import csmz.storm.zhu.diary.view.IAddDiaryView;
import csmz.storm.zhu.utils.ClickEffectUtil;

public class AddDiaryActivity extends CommonActivity implements IAddDiaryView, View.OnClickListener {

    private TextView tvTitle;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddDiaryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        initUI();
        setClickListener();
    }


    @Override
    public void initUI() {

        tvTitle = findView(R.id.toolbar_title);
        tvTitle.setText("添加日记");
    }

    @Override
    public void initData() {

    }

    @Override
    public void setClickListener() {
        //ClickEffectUtil.set(tvBack);
        //tvBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


    }
}
