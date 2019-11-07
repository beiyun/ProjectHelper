package com.beiyun.library.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;

import com.beiyun.library.util.Files;
import com.beiyun.library.util.Intents;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

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
    public Resources getResources(){
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
       return getVersionName(getPackageName());
    }

    @Override
    public String getVersionName(String packageName) {
        PackageManager manager = getPackageManager();
        String versionName = "";
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(),
                    0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }


    @Override
    public int getVersionCode() {
        return getVersionCode(getPackageName());
    }

    @Override
    public int getVersionCode(String packageName) {
        PackageManager manager = getPackageManager();
        int versionCode = 0;
        try {
            PackageInfo info = manager.getPackageInfo(packageName,
                    0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    @Override
    public String getAppName() {
        return getAppName(getPackageName());
    }

    @Override
    public String getAppName(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            @SuppressLint("WrongConstant") PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            return packageInfo.applicationInfo.loadLabel(pm).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void launchApp(String packageName) {
        getContext().startActivity(Intents.getLaunchAppIntent(packageName));
    }

    @Override
    public Drawable getAppIcon() {
        return getAppIcon(getPackageName());
    }

    @Override
    public Drawable getAppIcon(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            @SuppressLint("WrongConstant") PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            return packageInfo.applicationInfo.loadIcon(pm);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAppPath() {
        return getAppPath(getPackageName());
    }

    @Override
    public String getAppPath(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            @SuppressLint("WrongConstant") PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            return packageInfo.applicationInfo.sourceDir;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Signature[] getAppSignature() {
        return getAppSignature(getPackageName());
    }

    @Override
    public Signature[] getAppSignature(String packageName) {
        PackageManager pm = getPackageManager();
        try {
            @SuppressLint("WrongConstant") PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return new Signature[0];
    }

    @Override
    public boolean isSystemApp() {
        PackageManager pm = getPackageManager();
        try {
            @SuppressLint("WrongConstant") PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            return applicationInfo != null && (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)!= 0;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void installApp(String filePath,String authority) {
        getContext().startActivity(Intents.getInstallAppIntent(filePath,authority));


    }

    @Override
    public void unInstallApp(String packageName) {
        getContext().startActivity(Intents.getUninstallAppIntent(packageName));
    }

    @Override
    public boolean installAppSilent(String filePath) {
        if(filePath == null) return false;
        File file = Files.get(filePath);
        if (!Files.exists(file)) return false;
        String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm install " + filePath;
        Shells.CommandResult commandResult = Shells.execCmd(command, !isSystemApp(), true);
        return commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains("success");

    }

    @Override
    public boolean unInstallAppSilent(String packageName,boolean isKeepData) {
        if (packageName == null) return false;
        String command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib pm uninstall " + (isKeepData ? "-k " : "") + packageName;
        Shells.CommandResult commandResult = Shells.execCmd(command, !isSystemApp(), true);
        return commandResult.successMsg != null && commandResult.successMsg.toLowerCase().contains("success");
    }

    @Override
    public boolean isInstallApp(String packageName) {
        return packageName != null && Intents.getLaunchAppIntent(packageName) != null;
    }

    @Override
    public String getPackageName() {
        return getContext().getPackageName();
    }


    @Override
    public String getAppSignatureSHA1() {
        return getAppSignatureSHA1(getPackageName());
    }


    @Override
    public String getAppSignatureSHA1(final String packageName) {
        Signature[] signature = getAppSignature(packageName);
        if (signature == null) return null;
        return bytes2HexString(hashTemplate(signature[0].toByteArray(), "SHA1")).
                replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }


    private String bytes2HexString(final byte[] bytes) {
        if (bytes == null) return null;
        int len = bytes.length;
        if (len <= 0) return null;
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            ret[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(ret);
    }

    private static final char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    /**
     * hash加密模板
     *
     * @param data      数据
     * @param algorithm 加密算法
     * @return 密文字节数组
     */
    private static byte[] hashTemplate(final byte[] data, final String algorithm) {
        if (data == null || data.length <= 0) return null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(data);
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
