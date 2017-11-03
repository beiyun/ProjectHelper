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

    private static App app;

    @Deprecated
    protected static void init(Context context){
        app = AppsIml.init(context);
    }

    private static void ca(){
        if(app == null){
            throw new NullPointerException("----------the Apps class has no init---------------");
        }
    }


    public static Context getContext() {
        ca();
        return app.getContext();
    }

    
    public static Resources getResource() {
        ca();
        return app.getResource();
    }

    
    public static LayoutInflater getLayoutInflater() {
        ca();
        return app.getLayoutInflater();
    }

    
    public static String getString(@StringRes int resId) {
        ca();
        return app.getString(resId);
    }

    
    public static int getColor(@ColorRes int resId) {
        ca();
        return app.getColor(resId);
    }

    
    public static SharedPreferences getSharedPreferences(String spName, int spMode) {
        ca();
        return app.getSharedPreferences(spName,spMode);
    }

    
    public static ApplicationInfo getApplicationInfo() {
        ca();
        return app.getApplicationInfo();
    }

    
    public static File getExternalCacheDir() {
        ca();
        return app.getExternalCacheDir();
    }

    
    public static File getCacheDir() {
        ca();
        return app.getCacheDir();
    }

    
    public static ContentResolver getContentResolver() {
        ca();
        return app.getContentResolver();
    }

    
    public static PackageManager getPackageManager() {
        ca();
        return app.getPackageManager();
    }

    
    public static AssetManager getAssets() {
        ca();
        return app.getAssets();
    }

    
    public static Activity getCurrentActivity() {
        ca();
        return app.getCurrentActivity();
    }

    
    public static void finish() {
        ca();
        app.finish();
    }

    
    public static void finish(Activity activity) {
        ca();
        app.finish(activity);
    }

    
    public static void finish(Class<? extends Activity> cls) {
        ca();
        app.finish(cls);
    }

    
    public static Activity getActivity(Class<? extends Activity> cls) {
        ca();
        return app.getActivity(cls);
    }

    
    public static void exit() {
        ca();
        app.exit();
    }

    public static Object getSystemService(@NonNull String name){
        ca();
        return app.getSystemService(name);
    }

    public static Object getSystemService(Class<?> serviceClass){
        ca();
        return app.getSystemService(serviceClass);
    }
}
