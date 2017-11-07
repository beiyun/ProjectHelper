package com.beiyun.library.base;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
     * 获取app版本名
     * @param packageName 包名
     * @return app版本名
     */
   public abstract String getVersionName(String packageName);


    /**
     * 获取系统版本号
     * @return app获取版本号
     */
   public abstract int getVersionCode();


   /**
    * 获取app版本号
    * @param packageName 包名
    * @return 版本号
     */
   public abstract int getVersionCode(String packageName);
   /**
    * 获取app名称
    * @return appName
     */
   public abstract String getAppName();


   /**
    * 获取app名称
    * @param packageName 包名
    * @return APPName
     */
   public abstract String getAppName(String packageName);


   /**
    * 启动app
    * @param packageName 包名
     */
   public abstract void launchApp(String packageName);


    /**
     * 获取app图标
     * @return Drawable
     */
   public abstract Drawable getAppIcon();


    /**
     * 获取app图标
     * @param packageName 包名
     * @return APPIcon
     */
   public abstract Drawable getAppIcon(String packageName);


   /**
    * 获取app路径
    * @return app path
     */
   public abstract String getAppPath();


    /**
     * 获取app路径
     * @param packageName 包名
     * @return APPPath
     */
   public abstract String getAppPath(String packageName);


    /**
     * 获取app签名
     * @return Signatures
     */
   public abstract Signature[] getAppSignature();


   /**
    * 获取app签名
    * @return Signatures
    */
   public abstract Signature[] getAppSignature(String packageName);


    /**
     * 判断app是否为系统app
     * @return boolean true为是  false 为否
     */
   public abstract boolean isSystemApp();


    /**
     * 安装app
     * @param filePath 路径
     * @param authority 7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性
     *                  <br>参看https://developer.android.com/reference/android/support/v4/content/FileProvider.html
     */
   public abstract void installApp(String filePath,String authority);


   /**
    * 卸载app
    * @param packageName 包名
     */
   public abstract void unInstallApp(String packageName);


   /**
    * 静默安装App
    * <p>非root需添加权限 {@code <uses-permission android:name="android.permission.INSTALL_PACKAGES" />}</p>
    *
    * @param filePath 文件路径
    * @return {@code true}: 安装成功<br>{@code false}: 安装失败
    */
   public abstract boolean installAppSilent(String filePath);


   /**
    * 静默卸载App
    * <p>非root需添加权限 {@code <uses-permission android:name="android.permission.DELETE_PACKAGES" />}</p>
    *
    * @param packageName 包名
    * @param isKeepData  是否保留数据
    * @return {@code true}: 卸载成功<br>{@code false}: 卸载失败
    */
   public abstract boolean unInstallAppSilent(String packageName,boolean isKeepData);


    /**
     * app是否已安装
     * @param packageName packageName
     * @return boolean
     */
   public abstract boolean isInstallApp(String packageName);


   /**
    * 获取包名
    * @return String
     */
   public abstract String getPackageName();


   /**
    * 获取应用签名的的SHA1值
    * <p>可据此判断高德，百度地图key是否正确</p>
    *
    * @return 应用签名的SHA1字符串, 比如：53:FD:54:DC:19:0F:11:AC:B5:22:9E:F1:1A:68:88:1B:8B:E8:54:42
    */
   public abstract String getAppSignatureSHA1();


   /**
    * 获取应用签名的的SHA1值
    * <p>可据此判断高德，百度地图key是否正确</p>
    *
    * @param packageName 包名
    * @return 应用签名的SHA1字符串, 比如：53:FD:54:DC:19:0F:11:AC:B5:22:9E:F1:1A:68:88:1B:8B:E8:54:42
    */
   public abstract String getAppSignatureSHA1(String packageName);

}
