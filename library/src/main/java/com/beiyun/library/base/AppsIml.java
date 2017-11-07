package com.beiyun.library.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;

import java.io.File;

/**
 * Created by beiyun on 2017/11/1.
 * this class should register in application
 *
 */
class AppsIml extends App {

    private Context mContext;
    private static AppsIml appsIml;
    private ActivityManager mActivityManager;

    private AppsIml(Context context) {
        mContext = context;
        mActivityManager = ActivityManager.init(context);
    }

    protected static App init(Context context){
        if(appsIml == null){
            synchronized (AppsIml.class){
                if(appsIml == null){
                    appsIml = new AppsIml(context);
                }
            }
        }

        return appsIml;
    }

    /**
     * @return ApplicationContext
     */
    @Override
    public Context getContext() {
        return mContext;
    }

    /**
     * 获取Resource对象
     * @return Resource
     */
    @Override
    public Resources getResource(){
        return mContext.getResources();
    }


    /**
     *
     * @return LayoutInflater
     */
    @Override
    public LayoutInflater getLayoutInflater(){
        return (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public String getString(@StringRes int resId){
        return mContext.getString(resId);
    }


    /**
     *
     * @param resId
     * @return ColorId
     */
    @Override
    public int getColor(@ColorRes int resId){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return mContext.getColor(resId);
        }
        return mContext.getResources().getColor(resId);
    }


    /**
     *
     * @param spName
     * @param spMode
     * @return
     */
    @Override
    public SharedPreferences getSharedPreferences(String spName, int spMode){
        return mContext.getSharedPreferences(spName,spMode);
    }

    /**
     *
     * @return ApplicationInfo
     */
    @Override
    public ApplicationInfo getApplicationInfo(){
        return mContext.getApplicationInfo();
    }

    /**
     * @return File
     */
    @Override
    public File getExternalCacheDir(){
        return mContext.getExternalCacheDir();
    }


    /**
     * @return File
     */
    @Override
    public File getCacheDir(){
        return mContext.getCacheDir();
    }

    /**
     * @return ContentResolver
     */
    @Override
    public ContentResolver getContentResolver(){
        return mContext.getContentResolver();
    }


    /**
     * @return PackageManager
     */
    @Override
    public PackageManager getPackageManager(){
        return mContext.getPackageManager();
    }

    /**
     * @return AssetManager
     */
    @Override
    public AssetManager getAssets(){
        return mContext.getAssets();
    }


    //获取当前的activity
    @Override
    public Activity getCurrentActivity(){
       return mActivityManager.getCurrentActivity();
    }


    /**
     * 结束当前activity
     */
    @Override
    public void finish(){
        mActivityManager.finish();
    }


    /**
     * 结束指定的activity
     * @param activity
     */
    @Override
    public void finish(Activity activity) {
        mActivityManager.finish(activity);
    }


    //根据class关闭指定activity
    @Override
    public void finish(Class<? extends Activity> cls){
        mActivityManager.finish(cls);
    }


    //根据class文件获取activity
    @Override
    public Activity getActivity(Class<? extends Activity> cls){
       return mActivityManager.getActivity(cls);
    }


    //退出程序
    @Override
    public void exit(){
        mActivityManager.exit();
    }

    @Override
    public Object getSystemService(@NonNull String name) {
        return mContext.getSystemService(name);
    }


    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public Object getSystemService(Class<?> serviceClass){
        return  mContext.getSystemService(serviceClass);
    }


    @Override
    public String getVersionName() {

        PackageManager manager = getPackageManager();
        String versionName = "";
        try {
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(),
                    0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    @Override
    public int getVersionCode() {
        PackageManager manager = getPackageManager();
        int versionCode = 0;
        try {
            PackageInfo info = manager.getPackageInfo(getContext().getPackageName(),
                    0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }


}
