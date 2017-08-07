package csmz.storm.zhu.diary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import csmz.storm.zhu.R;

public class UpDataDiaryActivity extends AppCompatActivity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, UpDataDiaryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_data_diary);
    }
}
