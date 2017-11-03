package com.beiyun.library.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import com.beiyun.library.entity.SpMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by beiyun on 2017/11/3.
 * 偏好类操作
 */
public class Sps {

    private static final String TAG = "Sps";
    private SharedPreferences mSp;

    private Sps(String spName,SpMode spMode) {
        mSp = Apps.getSharedPreferences(spName,getSpMode(spMode));
    }


    public static Sps init(Class<?> cls, SpMode spMode){
        return init(cls.getName(),spMode);
    }

    public static Sps init(String spName,SpMode spMode){
        return new Sps(spName,spMode);
    }

    public static Sps init(String spName){
        return init(spName,SpMode.MODE_PRIVATE);
    }


    public static Sps init(Class<?> spCls){
        return init(spCls.getName(),SpMode.MODE_PRIVATE);
    }


    public static boolean save(@NonNull Object o){
        return save(o,SpMode.MODE_PRIVATE);
    }


    public static Object get(Class<?> cls){
        return get(cls,SpMode.MODE_PRIVATE);
    }

    /**
     *
     * @param o 将要保存的object
     * @return true 表示保存成功， false表示保存失败
     */
    public static boolean save(@NonNull Object o, SpMode spMode){
        Field[] fields = o.getClass().getDeclaredFields();
        Sps sps = Sps.init(o.getClass(),spMode);
        for (Field f:fields) {
            try {
                f.setAccessible(true);
                String fieldType = f.getGenericType().toString();
                if("class java.lang.String".equals(fieldType)){
                    sps.putString(f.getName(), String.valueOf(f.get(o)));
                }else if("int".equals(fieldType)){
                    sps.putInt(f.getName(),f.getInt(o));
                }else if("boolean".equals(fieldType)){
                    sps.putBoolean(f.getName(),f.getBoolean(o));
                }
            } catch (IllegalAccessException e) {
                Log.e(TAG, "save: "+e.getMessage() );
                return false;
            }
        }

        Log.e(TAG, "save: 成功" );
        return true;
    }




    //通过class文件找回保存的该class中所有保存的值
    public static Object get(Class<?> cls,SpMode spMode) {
        Sps sps = Sps.init(cls, spMode);
        try {
            Constructor<?> constructor = cls.getConstructor();
            Object o = constructor.newInstance();
            Field[] fields = cls.getDeclaredFields();
            for (Field f:fields) {
                f.setAccessible(true);
                String fieldType = f.getGenericType().toString();
                if("class java.lang.String".equals(fieldType)){
                    String stringValue = sps.getString(f.getName());
                    f.set(o,stringValue);
                }else if("int".equals(fieldType)){
                    int intValue = sps.getInt(f.getName());
                    f.setInt(o,intValue);
                }else if("boolean".equals(fieldType)){
                    boolean booleanValue = sps.getBoolean(f.getName());
                    f.setBoolean(o,booleanValue);
                }

            }

            Log.e(TAG, "get: "+o);

            return o;
        } catch (Exception e) {
            Log.e(TAG, "get: 没有默认的构造函数"+e.getMessage() );
            return null;
        }
    }


    public void putBoolean(String key, boolean booleanValue) {
        mSp.edit().putBoolean(key,booleanValue).apply();


    }

    public void putInt(String key, int intValue) {
        mSp.edit().putInt(key,intValue).apply();

    }

    public void putString(String key, String stringValue) {
        mSp.edit().putString(key,stringValue).apply();

    }
    
    public boolean getBoolean(String key){
        return mSp.getBoolean(key,false);
    }

    public int getInt(String key){
        return mSp.getInt(key,-1);
    }

    public String getString(String key){
        return mSp.getString(key,null);
    }

    private int getSpMode(SpMode spMode) {

        int mode = 0;
        switch (spMode){
            case MODE_APPEND:
                mode = Context.MODE_APPEND;
                break;
            case MODE_PRIVATE:
                mode = Context.MODE_PRIVATE;
                break;
            case MODE_NO_LOCALIZED_COLLATORS:
                if (Build.VERSION.SDK_INT >= 24) {
                    mode = Context.MODE_NO_LOCALIZED_COLLATORS;
                }else{
                    mode = Context.MODE_PRIVATE;
                }
                break;
            case MODE_ENABLE_WRITE_AHEAD_LOGGING:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mode = Context.MODE_ENABLE_WRITE_AHEAD_LOGGING;
                }else {
                    mode = Context.MODE_PRIVATE;
                }
                break;
        }

        return mode;
    }

}
