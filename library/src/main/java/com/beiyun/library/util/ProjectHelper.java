package com.beiyun.library.util;

import android.content.Context;

/**
 * Created by beiyun on 2017/11/3.
 */
public class ProjectHelper {

    private static ProjectHelper projectHelper;
    private ProjectHelper(Context context){
        Apps.init(context);


    }

    public static void init(Context context){
        if(projectHelper == null){
            synchronized (ProjectHelper.class){
                if(projectHelper == null){
                    new ProjectHelper(context);
                }
            }
        }
    }
}
