package csmz.storm.zhu.diary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import csmz.storm.zhu.R;
import csmz.storm.zhu.adapters.LinearLayoutColorDivider;
import csmz.storm.zhu.app.CommonFragment;
import csmz.storm.zhu.constants.Constants;
import csmz.storm.zhu.diary.db.DiaryDbHelper;
import csmz.storm.zhu.diary.mode.DiaryBean;
import csmz.storm.zhu.diary.view.IDiaryView;
import csmz.storm.zhu.utils.ClickEffectUtil;
import csmz.storm.zhu.utils.GetDateUtils;
import csmz.storm.zhu.utils.ToastUtil;

public class DiaryFragment extends CommonFragment implements IDiaryView, View.OnClickListener {

    private RecyclerView recyclerDiary;
    private FloatingActionButton fabAdd;
    private TextView tvEmpty;
    private DiaryDbHelper mDiaryDbHelper;
    private List<DiaryBean> mDiaryBeanList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diary_fragment, container, false);
        mDiaryDbHelper = new DiaryDbHelper(getActivity(), Constants.DB_NAME, null, 1);
        initUI(view);
        initData();
        setClickListener();
        return view;
    }

    @Override
    public void initUI(View view) {
        recyclerDiary = findView(R.id.recycler_diary, view);
        fabAdd = findView(R.id.fab_add, view);
        tvEmpty = findView(R.id.tv_query_none, view);
    }

    @Override
    public void initData() {
        mDiaryBeanList = new ArrayList<>();
        List<DiaryBean> diaryList = new ArrayList<>();
        SQLiteDatabase sqliteDatabase = mDiaryDbHelper.getWritableDatabase();
        Cursor cursor = sqliteDatabase.query("Diary", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String tag = cursor.getString(cursor.getColumnIndex("tag"));
                mDiaryBeanList.add(new DiaryBean(date, title, content, tag));

            } while (cursor.moveToNext());
        }
        cursor.close();
        for (int i = mDiaryBeanList.size() - 1; i >= 0; i--) {
            diaryList.add(mDiaryBeanList.get(i));
        }
        mDiaryBeanList = diaryList;
        tvEmpty.setVisibility(mDiaryBeanList.size() == 0 ? View.VISIBLE : View.GONE);
        recyclerDiary.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerDiary.addItemDecoration(new LinearLayoutColorDivider(getResources(), R.color.gray_black, R.dimen.line_height, LinearLayoutManager.VERTICAL));
        recyclerDiary.setAdapter(new DiaryAdapter(getActivity(), mDiaryBeanList));
    }

    @Override
    public void setClickListener() {
        ClickEffectUtil.set(fabAdd);
        fabAdd.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getActivity(), AddDiaryActivity.class));
    }
}
