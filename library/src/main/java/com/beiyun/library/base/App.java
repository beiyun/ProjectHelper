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
 *继承了context的方法和一些app相关工具
 */
abstract class App {


   /**
    * 此处获取的是applicationContext
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


   /**
    * 通过resId获取字串
    * @param resId
    * @return String
     */
   public abstract String getString(@StringRes int resId);


   /**
    *获取color
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


   /**
    * 获取当前的activity
    * @return Activity
    */
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


   /**
    * 根据class关闭指定activity
    * @param cls
     */
   public abstract void finish(Class<? extends Activity> cls);


   /**
    * 根据class文件获取activity
    * @param cls
    * @return Activity
     */
   public abstract Activity getActivity(Class<? extends Activity> cls);


   /**
    * 退出程序
    */
   public abstract void exit();


   /**
    * 获取系统服务
    * @param name eg: Context.LAYOUT_INFLATER_SERVICE
    * @return Object
    */
   public abstract Object getSystemService(@NonNull String name);


   /**
    * 获取系统服务
    * @param serviceClass eg: Context.LAYOUT_INFLATER_SERVICE
    * @return Object
     */
   public abstract Object getSystemService(Class<?> serviceClass);


   /**
    * 获取app版本名
    * @return 版本名
     */
   public abstract String getVersionName();


    /**
     * 获取系统版本号
     * @return app获取版本号
     */
   public abstract int getVersionCode();





}
