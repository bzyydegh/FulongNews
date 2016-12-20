package cn.edu.gdaib.fulongnews.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * 提示
 * Created by ZYongY
 * on 2016/11/07.
 */
public class PromptUtil {
    //Snackbar提示信息
    public static void SnackbarPrompt(View parentView, String info) {
        Snackbar.make(parentView, info, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    //Toast提示信息
    public static void ToastPrompt(Context context, String info, int time) {
        Toast.makeText(context, info, time).show();
    }
}
