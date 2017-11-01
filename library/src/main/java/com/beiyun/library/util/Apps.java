package com.beiyun.library.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;

import java.io.File;

/**
 * Created by beiyun on 2017/11/1.
 */
public class Apps {

    private static Context mContext;
    private static Apps apps;

    private Apps(Context context) {
        mContext = context;
    }





    public static Apps register(Context context){
        if(apps == null){
            synchronized (Apps.class){
                if(apps == null){
                    apps = new Apps(context);
                }
            }
        }

        return apps;
    }

    /**
     * @return ApplicationContext
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取Resource对象
     * @return Resource
     */
    public static Resources getResource(){
        return mContext.getResources();
    }


    /**
     *
     * @return LayoutInflater
     */
    public static LayoutInflater getLayoutInflater(){
        return (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public static String getString(@StringRes int resId){
        return mContext.getString(resId);
    }


    /**
     *
     * @param resId
     * @return ColorId
     */
    public static int getColor(@ColorRes int resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mContext.getColor(resId);
        }
        return mContext.getResources().getColor(resId,null);
    }


    /**
     *
     * @param spName
     * @param spMode
     * @return
     */
    public static SharedPreferences getSharedPreferences(String spName, int spMode){
        return mContext.getSharedPreferences(spName,spMode);
    }

    /**
     *
     * @return ApplicationInfo
     */
    public static ApplicationInfo getApplicationInfo(){
        return mContext.getApplicationInfo();
    }

    /**
     * @return File
     */
    public static File getExternalCacheDir(){
        return mContext.getExternalCacheDir();
    }


    /**
     * @return File
     */
    public static File getCacheDir(){
        return mContext.getCacheDir();
    }

    /**
     * @return ContentResolver
     */
    public static ContentResolver getContentResolver(){
        return mContext.getContentResolver();
    }


    /**
     * @return PackageManager
     */
    public static PackageManager getPackageManager(){
        return mContext.getPackageManager();
    }
}
