package csmz.storm.zhu.diary;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.TextView;

import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;
import csmz.storm.zhu.R;
import csmz.storm.zhu.app.CommonActivity;
import csmz.storm.zhu.diary.db.DiaryDbHelper;
import csmz.storm.zhu.diary.presenter.AddDiaryPresenter;
import csmz.storm.zhu.diary.presenter.AddDiaryPresenterComp;
import csmz.storm.zhu.diary.view.IAddDiaryView;
import csmz.storm.zhu.diary.widget.LinedEditText;
import csmz.storm.zhu.utils.ClickEffectUtil;
import csmz.storm.zhu.utils.GetDateUtils;
import csmz.storm.zhu.utils.ToastUtil;

public class AddDiaryActivity extends CommonActivity implements IAddDiaryView, View.OnClickListener {

    private TextView tvTitle, tvTime;
    private Toolbar toolbar;
    private EditText edTitle;
    private LinedEditText edContent;
    private FloatingActionButton addButton, backButton;
    private AddDiaryPresenter addDiaryPresenter;
    private DiaryDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        initUI();
        initData();
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

        edTitle = findView(R.id.ed_title);
        edContent = findView(R.id.et_content);

    }

    @Override
    public void initData() {
        dbHelper = new DiaryDbHelper(this, "Diary.db", null, 1);
    }


    @Override
    public void setClickListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backDiaryFragment();
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
                final String date = GetDateUtils.getDate().toString();
                String tag = String.valueOf(System.currentTimeMillis());
                String title = edTitle.getText().toString() + "";
                String content = edContent.getText().toString() + "";
                if (!title.equals("") || !content.equals("")) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("tag", tag);
                    values.put("title", title);
                    values.put("content", content);
                    values.put("date", date);
                    db.insert("Diary", null, values);

                    values.clear();
                    finish();
                } else {
                    ToastUtil.showShort(getApplicationContext(), "记录生活，记录你！");
                }
                break;

            case R.id.fab_back:
                backDiaryFragment();
                break;
            default:
                break;
        }

    }

    public void backDiaryFragment() {
        final String date = GetDateUtils.getDate().toString();
        final String title = edTitle.getText().toString();
        final String content = edContent.getText().toString();
        if (!title.isEmpty() || !content.isEmpty()) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("是否保存日记内容？")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("date", date);
                            values.put("title", title);
                            values.put("content", content);
                            db.insert("Diary", null, values);
                            values.clear();
                            finish();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).show();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
