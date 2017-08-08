package csmz.storm.zhu.diary;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;
import csmz.storm.zhu.R;
import csmz.storm.zhu.app.CommonActivity;
import csmz.storm.zhu.diary.presenter.AddDiaryPresenter;
import csmz.storm.zhu.diary.presenter.AddDiaryPresenterComp;
import csmz.storm.zhu.diary.view.IAddDiaryView;
import csmz.storm.zhu.diary.widget.LinedEditText;
import csmz.storm.zhu.utils.ClickEffectUtil;
import csmz.storm.zhu.utils.GetDateUtils;

public class AddDiaryActivity extends CommonActivity implements IAddDiaryView, View.OnClickListener {

    private TextView tvTitle, tvTime;
    private Toolbar toolbar;
    private EditText edTitle;
    private LinedEditText edContent;
    private FloatingActionButton addButton, backButton;
    private FloatingActionsMenu mRightLabels;

    private AddDiaryPresenter addDiaryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        initUI();
        setClickListener();
    }


    @Override
    public void initUI() {
        addDiaryPresenter = new AddDiaryPresenterComp();

        toolbar = findView(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.app_back);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tvTitle = findView(R.id.toolbar_title);
        tvTitle.setText("添加日记");
        tvTime = findView(R.id.add_time);
        tvTime.setText("今天," + GetDateUtils.getDate());

        addButton = findView(R.id.fab_add);
        backButton = findView(R.id.fab_back);
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
            }
        });
        addButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

        ClickEffectUtil.set(toolbar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add:

                break;

            case R.id.fab_back:
                finish();
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
