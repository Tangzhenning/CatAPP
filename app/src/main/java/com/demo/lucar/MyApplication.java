package com.demo.lucar;

import android.app.Application;

import com.demo.lucar.bean.MyObjectBox;

import io.objectbox.BoxStore;

public class MyApplication extends Application {
    private static MyApplication application;
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public static MyApplication getApplicationInstance() {
        return application;
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
