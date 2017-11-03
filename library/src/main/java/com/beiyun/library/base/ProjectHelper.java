package com.beiyun.library.base;

import android.content.Context;

/**
 * Created by beiyun on 2017/11/3.
 * 在application中进行初始化
 */
public class ProjectHelper {

    private static App app;

    private ProjectHelper(Context context){
        app = AppsIml.init(context);
    }

    public static void init(Context context){
        new ProjectHelper(context);
    }

    public static App getApp() {
        if(app == null){
            throw new NullPointerException("----------the ProjectHelper class has no init---------------");
        }
        return app;
    }

}
