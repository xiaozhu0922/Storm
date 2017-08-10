package csmz.storm.zhu.app;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import csmz.storm.zhu.diary.UpDateDiaryActivity;
import csmz.storm.zhu.diary.event.StartUpdateDiaryEvent;
import csmz.storm.zhu.diary.mode.DiaryBean;

/**
 * Created by uniface on 2017/8/2.
 */

public class CommonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    public <T extends View> T findView(int id) {
        T view = (T) findViewById(id);
        return view;
    }

    public <T extends View> T findView(int id, View root_view) {
        T view = (T) root_view.findViewById(id);
        return view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Subscribe
    public void startUpdateDiaryActivity(StartUpdateDiaryEvent event) {
        DiaryBean diaryBean = event.getDiaryBean();
        String title = diaryBean.getTitle();
        String content = diaryBean.getContent();
        String tag = diaryBean.getTag();
        UpDateDiaryActivity.startActivity(this, title, content, tag);
    }
}
