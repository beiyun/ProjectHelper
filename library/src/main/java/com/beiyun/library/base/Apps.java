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
public class Apps{


    public static Context getContext() {
        return ProjectHelper.getApp().getContext();
    }

    
    public static Resources getResource() {
        return ProjectHelper.getApp().getResource();
    }

    
    public static LayoutInflater getLayoutInflater() {
        return ProjectHelper.getApp().getLayoutInflater();
    }

    
    public static String getString(@StringRes int resId) {
        return ProjectHelper.getApp().getString(resId);
    }

    
    public static int getColor(@ColorRes int resId) {
        return ProjectHelper.getApp().getColor(resId);
    }

    
    public static SharedPreferences getSharedPreferences(String spName, int spMode) {
        return ProjectHelper.getApp().getSharedPreferences(spName,spMode);
    }

    
    public static ApplicationInfo getApplicationInfo() {
        return ProjectHelper.getApp().getApplicationInfo();
    }

    
    public static File getExternalCacheDir() {
        return ProjectHelper.getApp().getExternalCacheDir();
    }

    
    public static File getCacheDir() {
        return ProjectHelper.getApp().getCacheDir();
    }

    
    public static ContentResolver getContentResolver() {
        return ProjectHelper.getApp().getContentResolver();
    }

    
    public static PackageManager getPackageManager() {
        return ProjectHelper.getApp().getPackageManager();
    }

    
    public static AssetManager getAssets() {
        return ProjectHelper.getApp().getAssets();
    }

    
    public static Activity getCurrentActivity() {
        return ProjectHelper.getApp().getCurrentActivity();
    }

    
    public static void finish() {
        ProjectHelper.getApp().finish();
    }

    
    public static void finish(Activity activity) {
        ProjectHelper.getApp().finish(activity);
    }

    
    public static void finish(Class<? extends Activity> cls) {
        ProjectHelper.getApp().finish(cls);
    }

    
    public static Activity getActivity(Class<? extends Activity> cls) {
        return ProjectHelper.getApp().getActivity(cls);
    }

    
    public static void exit() {
        ProjectHelper.getApp().exit();
    }


    public static Object getSystemService(@NonNull String name){
        return ProjectHelper.getApp().getSystemService(name);
    }


    public static Object getSystemService(Class<?> serviceClass){
        return ProjectHelper.getApp().getSystemService(serviceClass);
    }
}
