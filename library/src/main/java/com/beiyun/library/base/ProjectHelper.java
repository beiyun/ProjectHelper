package com.beiyun.library.base;

import android.content.Context;

/**
 * Created by beiyun on 2017/11/3.
 * 在application中进行初始化
 */
public class ProjectHelper {

    private ProjectHelper(Context context){
        Apps.init(context);
    }

    public static void init(Context context){
        new ProjectHelper(context);
    }
}
