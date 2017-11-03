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
 abstract class App {


    /**
     * @return ApplicationContext
     */
     protected abstract Context getContext();

    /**
     * 获取Resource对象
     * @return Resource
     */
    protected abstract Resources getResource();


    /**
     *
     * @return LayoutInflater
     */
    protected abstract LayoutInflater getLayoutInflater();


    protected abstract String getString(@StringRes int resId);


    /**
     *
     * @param resId
     * @return ColorId
     */
    protected abstract int getColor(@ColorRes int resId);


    /**
     *
     * @param spName
     * @param spMode
     * @return
     */
    protected abstract SharedPreferences getSharedPreferences(String spName, int spMode);

    /**
     *
     * @return ApplicationInfo
     */
    protected abstract ApplicationInfo getApplicationInfo();

    /**
     * @return File
     */
    protected abstract File getExternalCacheDir();


    /**
     * @return File
     */
    protected abstract File getCacheDir();

    /**
     * @return ContentResolver
     */
    protected abstract ContentResolver getContentResolver();


    /**
     * @return PackageManager
     */
    protected abstract PackageManager getPackageManager();

    /**
     * @return AssetManager
     */
    protected abstract AssetManager getAssets();






    //获取当前的activity
    protected abstract Activity getCurrentActivity();


    /**
     * 结束当前activity
     */
    protected abstract void finish();


    /**
     * 结束指定的activity
     * @param activity
     */
    protected abstract void finish(Activity activity);


    //根据class关闭指定activity
    protected abstract void finish(Class<? extends Activity> cls);


    //根据class文件获取activity
    protected abstract Activity getActivity(Class<? extends Activity> cls);



    //退出程序
    protected abstract void exit();


    protected abstract Object getSystemService(@NonNull String name);


    protected abstract Object getSystemService(Class<?> serviceClass);

}
