package com.beiyun.projecthelper;

import android.app.Application;

import com.beiyun.library.util.Apps;

/**
 * Created by beiyun on 2017/11/1.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Apps.register(this);
    }
}
