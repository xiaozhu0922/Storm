package csmz.storm.zhu.diary;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import cc.trity.floatingactionbutton.FloatingActionButton;
import csmz.storm.zhu.R;
import csmz.storm.zhu.app.CommonActivity;
import csmz.storm.zhu.diary.db.DiaryDbHelper;
import csmz.storm.zhu.diary.view.IUpdateDiaryView;
import csmz.storm.zhu.diary.widget.LinedEditText;
import csmz.storm.zhu.utils.ClickEffectUtil;
import csmz.storm.zhu.utils.GetDateUtils;

public class UpDateDiaryActivity extends CommonActivity implements IUpdateDiaryView, View.OnClickListener {
    private TextView tvTitle, tvTime, tvTag;
    private Toolbar toolbar;
    private EditText edTitle;
    private LinedEditText edContent;
    private FloatingActionButton backButton, addButton, delButton;

    private DiaryDbHelper dbHelper;

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
        dbHelper = new DiaryDbHelper(this, "Diary.db", null, 1);
        initUI();
        Intent intent = getIntent();
        initData(intent);
        setClickListener();
    }

    @Override
    public void initUI() {
        toolbar = findView(R.id.toolbar);
        edTitle = findView(R.id.ed_title);
        edContent = findView(R.id.et_content);
        tvTag = findView(R.id.tv_tag);
        toolbar.setNavigationIcon(R.drawable.app_back);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tvTitle = findView(R.id.toolbar_title);
        tvTitle.setText("修改日记");
        tvTime = findView(R.id.time);
        tvTime.setText("今天," + GetDateUtils.getDate());


        backButton = findView(R.id.fab_back);
        addButton = findView(R.id.fab_add);
        delButton = findView(R.id.fab_delete);
    }

    @Override
    public void initData(Intent intent) {
        edTitle.setText(intent.getStringExtra("title"));
        edContent.setText(intent.getStringExtra("content"));
        tvTag.setText(intent.getStringExtra("tag"));
    }

    @Override
    public void setClickListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
        backButton.setOnClickListener(this);
        addButton.setOnClickListener(this);
        delButton.setOnClickListener(this);
        ClickEffectUtil.set(toolbar);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.fab_back:
                finish();
                break;

            case R.id.fab_add:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                String title = edTitle.getText().toString();
                String content = edContent.getText().toString();
                values.put("title", title);
                values.put("content", content);
                db.update("Diary", values, "title=?", new String[]{title});
                db.update("Diary", values, "content=?", new String[]{content});
                finish();
                break;

            case R.id.fab_delete:
                deleteDiary();
                break;


        }

    }

    private void deleteDiary() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("确定要删除日记吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tag = tvTag.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Diary", "tag=?", new String[]{tag});
                finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
