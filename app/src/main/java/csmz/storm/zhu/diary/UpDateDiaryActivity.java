package csmz.storm.zhu.diary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import csmz.storm.zhu.R;

public class UpDateDiaryActivity extends AppCompatActivity {

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
    }
}
