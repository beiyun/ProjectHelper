package com.beiyun.projecthelper.base;

import android.app.Application;

import com.beiyun.library.base.ProjectHelper;

/**
 * Created by beiyun on 2017/11/1.
 *
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ProjectHelper.init(this);
    }



}
