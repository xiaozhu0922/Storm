package csmz.storm.zhu.app;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by uniface on 2017/8/2.
 */

public class CommonActivity extends AppCompatActivity {


    public <T extends View> T findView(int id) {
        T view = (T) findViewById(id);
        return view;
    }

    public <T extends View> T findView(int id, View root_view) {
        T view = (T) root_view.findViewById(id);
        return view;
    }


}
