package com.demo.lucar.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


public class ToastUtils {
    private static Toast mToast;
    private static String TAG = "cs";

    public static void showMes(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public static void showLog(String msg) {
        Log.d(TAG, msg);
    }
}

