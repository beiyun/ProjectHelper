package com.beiyun.library.util;

import android.util.Log;

/**
 * Created by beiyun on 2017/11/7.
 * log 打印
 */
public class Logs {

    private static String TAG;

    static {
        TAG = Apps.getAppName()+"-->";
    }

    public static void i(String msg){
        i(msg,null);
    }

    public static void i(String msg,Throwable t){
        Log.e(TAG, msg, t);
    }

    public static void d(String msg){
        Log.d(TAG, msg);
    }


    public static void e(String msg){
        e(msg,null);
    }

    public static void e(String msg,Throwable tr){
        Log.e(TAG, msg,tr);
    }

    public static void w(String msg){
        w(msg,null);
    }

    public static void w(String msg,Throwable tr){
        Log.w(TAG, msg, tr);
    }

}
