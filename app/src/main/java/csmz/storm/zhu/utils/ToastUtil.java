package csmz.storm.zhu.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static Toast toast;

    public static void showShort(Context context, CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void showShort(Context context, int message) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void show(Context context, CharSequence message, int duration) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, duration);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void show(Context context, int message, int duration) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, duration);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}
