package com.demo.lucar.activity;

import android.support.v7.app.AppCompatActivity;

import com.demo.lucar.utils.ToastUtils;

public class BaseActivity extends AppCompatActivity {
    protected void showToast(String msg) {
        ToastUtils.showMes(this, msg);
    }
}
