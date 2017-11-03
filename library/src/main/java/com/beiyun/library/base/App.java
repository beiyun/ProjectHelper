package com.beiyun.library.base;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;

import java.io.File;

/**
 * Created by beiyun on 2017/11/3.
 *
 */
 public abstract class App {


    /**
     * @return ApplicationContext
     */
     public abstract Context getContext();

    /**
     * 获取Resource对象
     * @return Resource
     */
    public abstract Resources getResource();


    /**
     *
     * @return LayoutInflater
     */
    public abstract LayoutInflater getLayoutInflater();


    public abstract String getString(@StringRes int resId);


    /**
     *
     * @param resId
     * @return ColorId
     */
    public abstract int getColor(@ColorRes int resId);


    /**
     *
     * @param spName
     * @param spMode
     * @return
     */
    public abstract SharedPreferences getSharedPreferences(String spName, int spMode);

    /**
     *
     * @return ApplicationInfo
     */
    public abstract ApplicationInfo getApplicationInfo();

    /**
     * @return File
     */
    public abstract File getExternalCacheDir();


    /**
     * @return File
     */
    public abstract File getCacheDir();

    /**
     * @return ContentResolver
     */
    public abstract ContentResolver getContentResolver();


    /**
     * @return PackageManager
     */
    public abstract PackageManager getPackageManager();

    /**
     * @return AssetManager
     */
    public abstract AssetManager getAssets();






    //获取当前的activity
    public abstract Activity getCurrentActivity();


    /**
     * 结束当前activity
     */
    public abstract void finish();


    /**
     * 结束指定的activity
     * @param activity
     */
    public abstract void finish(Activity activity);


    //根据class关闭指定activity
    public abstract void finish(Class<? extends Activity> cls);


    //根据class文件获取activity
    public abstract Activity getActivity(Class<? extends Activity> cls);



    //退出程序
    public abstract void exit();


    public abstract Object getSystemService(@NonNull String name);


    public abstract Object getSystemService(Class<?> serviceClass);

}
