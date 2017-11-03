package com.beiyun.library.iml;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;

import java.io.File;

/**
 * Created by beiyun on 2017/11/1.
 * this class should register in application
 *
 */
public class AppsIml extends App {

    private Context mContext;
    private static AppsIml appsIml;
    private ActivityManager mActivityManager;

    private AppsIml(Context context) {
        mContext = context;
        mActivityManager = ActivityManager.init(context);
    }

    public static App init(Context context){
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
    public Context getContext() {
        return mContext;
    }

    /**
     * 获取Resource对象
     * @return Resource
     */
    public Resources getResource(){
        return mContext.getResources();
    }


    /**
     *
     * @return LayoutInflater
     */
    public LayoutInflater getLayoutInflater(){
        return (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public String getString(@StringRes int resId){
        return mContext.getString(resId);
    }


    /**
     *
     * @param resId
     * @return ColorId
     */
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
    public SharedPreferences getSharedPreferences(String spName, int spMode){
        return mContext.getSharedPreferences(spName,spMode);
    }

    /**
     *
     * @return ApplicationInfo
     */
    public ApplicationInfo getApplicationInfo(){
        return mContext.getApplicationInfo();
    }

    /**
     * @return File
     */
    public File getExternalCacheDir(){
        return mContext.getExternalCacheDir();
    }


    /**
     * @return File
     */
    public File getCacheDir(){
        return mContext.getCacheDir();
    }

    /**
     * @return ContentResolver
     */
    public ContentResolver getContentResolver(){
        return mContext.getContentResolver();
    }


    /**
     * @return PackageManager
     */
    public PackageManager getPackageManager(){
        return mContext.getPackageManager();
    }

    /**
     * @return AssetManager
     */
    public AssetManager getAssets(){
        return mContext.getAssets();
    }






    //获取当前的activity
    public Activity getCurrentActivity(){
       return mActivityManager.getCurrentActivity();
    }


    /**
     * 结束当前activity
     */
    public void finish(){
        mActivityManager.finish();
    }


    /**
     * 结束指定的activity
     * @param activity
     */
    public void finish(Activity activity) {
        mActivityManager.finish(activity);
    }


    //根据class关闭指定activity
    public void finish(Class<? extends Activity> cls){
        mActivityManager.finish(cls);
    }


    //根据class文件获取activity
    public Activity getActivity(Class<? extends Activity> cls){
       return mActivityManager.getActivity(cls);
    }


    //退出程序
    public void exit(){
        mActivityManager.exit();
    }


}
