package csmz.storm.zhu.app;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import csmz.storm.zhu.R;

/**
 * Created by uniface on 2017/8/2.
 */

public class CommonFragment extends Fragment {
    private AlertDialog myDialog;

    public <T extends View> T findView(int id, View root_view) {
        T view = (T) root_view.findViewById(id);
        return view;
    }

    /**
     * 转圈圈
     *
     * @param str
     */
    public void showDialog(String str) {
        View view_loading = getActivity().getLayoutInflater().inflate(R.layout.dialog_loading, null);
        TextView tv_loading = findView(R.id.tv_loading, view_loading);
        tv_loading.setText(str);
        myDialog = new AlertDialog.Builder(getActivity(), R.style.dialog_notitle).setView(view_loading).create();
        myDialog.show();
    }

    /**
     * 转圈圈消失
     */
    public void dismissDialog() {
        if (null != myDialog && myDialog.isShowing()) {
            myDialog.dismiss();
        }
    }
}
