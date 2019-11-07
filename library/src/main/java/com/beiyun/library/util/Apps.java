package com.beiyun.library.util;

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
import android.view.LayoutInflater;

import com.beiyun.library.base.App;
import com.beiyun.library.base.ProjectHelper;

import java.io.File;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

/**
 * Created by beiyun on 2017/11/3.
 * 包函Context操作和其他操作
 */
public class Apps{


    public static Context getContext() {
        return getApp().getContext();
    }

    
    public static Resources getResources() {
        return getApp().getResources();
    }

    
    public static LayoutInflater getLayoutInflater() {
        return getApp().getLayoutInflater();
    }

    
    public static String getString(@StringRes int resId) {
        return getApp().getString(resId);
    }

    
    public static int getColor(@ColorRes int resId) {
        return getApp().getColor(resId);
    }

    
    public static SharedPreferences getSharedPreferences(String spName, int spMode) {
        return getApp().getSharedPreferences(spName,spMode);
    }

    
    public static ApplicationInfo getApplicationInfo() {
        return getApp().getApplicationInfo();
    }

    
    public static File getExternalCacheDir() {
        return getApp().getExternalCacheDir();
    }

    
    public static File getCacheDir() {
        return getApp().getCacheDir();
    }

    
    public static ContentResolver getContentResolver() {
        return getApp().getContentResolver();
    }

    
    public static PackageManager getPackageManager() {
        return getApp().getPackageManager();
    }

    
    public static AssetManager getAssets() {
        return getApp().getAssets();
    }


    /**
     * 获取当前的activity
     * @return Activity
     */
    public static Activity getCurrentActivity() {
        return getApp().getCurrentActivity();
    }


    /**
     * 结束当前activity
     */
    public static void finish() {
        getApp().finish();
    }


    /**
     * 结束指定的activity
     * @param activity
     */
    public static void finish(Activity activity) {
        getApp().finish(activity);
    }


    /**
     * 根据class关闭指定activity
     * @param cls
     */
    public static void finish(Class<? extends Activity> cls) {
        getApp().finish(cls);
    }


    /**
     * 根据class文件获取activity
     * @param cls
     * @return Activity
     */
    public static Activity getActivity(Class<? extends Activity> cls) {
        return getApp().getActivity(cls);
    }

    /**
     * 退出程序
     */
    public static void exit() {
        getApp().exit();
    }


    /**
     * 获取系统服务
     * @param name eg: Context.LAYOUT_INFLATER_SERVICE
     * @return Object
     */
    public static Object getSystemService(@NonNull String name){
        return getApp().getSystemService(name);
    }


    /**
     * 获取系统服务
     * @param serviceClass serviceClass
     * @return Object
     */
    public static Object getSystemService(Class<?> serviceClass){
        return getApp().getSystemService(serviceClass);
    }


    /**
     * 获取app版本名
     * @return 版本名
     */
    public static String getVersionName(){
        return getApp().getVersionName();
    }


    /**
     * 获取app版本名
     * @param packageName 包名
     * @return app版本名
     */
    public static String getVersionName(String packageName){
        return getApp().getVersionName(packageName);
    }


    /**
     * 获取系统版本号
     * @return app获取版本号
     */
    public static int getVersionCode(){
        return getApp().getVersionCode();
    }


    /**
     * 获取app版本号
     * @param packageName 包名
     * @return 版本号
     */
    public static int getVersionCode(String packageName){
        return getApp().getVersionCode(packageName);
    }


    /**
     * 获取app名称
     * @return appName
     */
    public static String getAppName(){
        return getApp().getAppName();
    }


    /**
     * 获取app名称
     * @param packageName 包名
     * @return APPName
     */
    public static String getAppName(String packageName){
        return getApp().getAppName(packageName);
    }


    /**
     * 启动app
     * @param packageName 包名
     */
    public static void launchApp(String packageName){
        getApp().launchApp(packageName);
    }


    /**
     * 获取app图标
     * @return Drawable
     */
    public static Drawable getAppIcon(){
       return getApp().getAppIcon();
    }


    /**
     * 获取app图标
     * @param packageName 包名
     * @return APPIcon
     */
    public static Drawable getAppIcon(String packageName){
        return getApp().getAppIcon(packageName);
    }


    /**
     * 获取app路径
     * @return app path
     */
    public static String getAppPath(){
        return getApp().getAppPath();
    }


    /**
     * 获取app路径
     * @param packageName 包名
     * @return APPPath
     */
    public static String getAppPath(String packageName){
        return getApp().getAppPath();
    }


    /**
     * 获取app签名
     * @return Signatures
     */
    public static Signature[] getAppSignature(){
        return getApp().getAppSignature();
    }


    /**
     * 获取app签名
     * @return Signatures
     */
    public static Signature[] getAppSignature(String packageName){
        return getApp().getAppSignature(packageName);
    }


    /**
     * 判断app是否为系统app
     * @return boolean true为是  false 为否
     */
    public static boolean isSystemApp(){
        return getApp().isSystemApp();
    }


    /**
     * 安装app
     * @param filePath 路径
     * @param authority 7.0及以上安装需要传入清单文件中的{@code <provider>}的authorities属性
     *                  <br>参看https://developer.android.com/reference/android/support/v4/content/FileProvider.html
     */
    public static void installApp(String filePath,String authority){
        getApp().installApp(filePath,authority);
    }


    /**
     * 卸载app
     * @param packageName 包名
     */
    public static void unInstallApp(String packageName){
        getApp().unInstallApp(packageName);
    }


    /**
     * 静默安装App
     * <p>非root需添加权限 {@code <uses-permission android:name="android.permission.INSTALL_PACKAGES" />}</p>
     *
     * @param filePath 文件路径
     * @return {@code true}: 安装成功<br>{@code false}: 安装失败
     */
    public static boolean installAppSilent(String filePath){
        return getApp().installAppSilent(filePath);
    }


    /**
     * 静默卸载App
     * <p>非root需添加权限 {@code <uses-permission android:name="android.permission.DELETE_PACKAGES" />}</p>
     *
     * @param packageName 包名
     * @param isKeepData  是否保留数据
     * @return {@code true}: 卸载成功<br>{@code false}: 卸载失败
     */
    public static boolean unInstallAppSilent(String packageName,boolean isKeepData){
        return getApp().unInstallAppSilent(packageName,isKeepData);
    }


    /**
     * app是否已安装
     * @param packageName packageName
     * @return boolean
     */
    public static boolean isInstallApp(String packageName){
        return getApp().isInstallApp(packageName);
    }


    /**
     * 获取包名
     * @return String
     */
    public static String getPackageName(){
        return getApp().getPackageName();
    }


    /**
     * 获取应用签名的的SHA1值
     * <p>可据此判断高德，百度地图key是否正确</p>
     *
     * @return 应用签名的SHA1字符串, 比如：53:FD:54:DC:19:0F:11:AC:B5:22:9E:F1:1A:68:88:1B:8B:E8:54:42
     */
    public static String getAppSignatureSHA1(){
        return getApp().getAppSignatureSHA1();
    }


    /**
     * 获取应用签名的的SHA1值
     * <p>可据此判断高德，百度地图key是否正确</p>
     *
     * @param packageName 包名
     * @return 应用签名的SHA1字符串, 比如：53:FD:54:DC:19:0F:11:AC:B5:22:9E:F1:1A:68:88:1B:8B:E8:54:42
     */
    public static String getAppSignatureSHA1(String packageName){
        return getApp().getAppSignatureSHA1(packageName);
    }


    private static App getApp(){
        return ProjectHelper.getApp();
    }
}
