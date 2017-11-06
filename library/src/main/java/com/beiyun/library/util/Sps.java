package com.beiyun.library.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import com.beiyun.library.entity.SpMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Created by beiyun on 2017/11/3.
 * 偏好类操作
 */
public class Sps {

    private static final String TAG = "Sps";
    private SharedPreferences mSp;
    private Class<?> mCls;
    private Object mObject;

    /**
     * Sps构造器
     * @param spName
     * @param o
     * @param cls
     * @param spMode
     */
    private Sps(String spName,Object o,Class<?> cls,SpMode spMode){
        this.mCls = cls;
        this.mObject = o;
        String name = null;
        if(spName != null){
            name = spName;
        }else if(mCls != null){
            name = mCls.getName();
        }else if(mObject != null){
            name = mObject.getClass().getName();
        }else{
            throw new NullPointerException("this spName cannot be null");
        }
        mSp = Apps.getSharedPreferences(name,spMode == null? getSpMode(SpMode.MODE_PRIVATE):getSpMode(spMode));
    }


    /**
     * 若使用Sps的一般方法，需调用此方法进行初始化
     * @return SpsBuilder
     */
    public static SpsBuilder init(){
        return new Sps.SpsBuilder();
    }


    /**
     * 保存 object
     * @return true 保存成功  false 保存失败
     */
    public boolean save(){
        if(mObject == null){
            throw new NullPointerException("the object that you'll save is null");
        }
        return save(mObject,this);
    }

    /**
     * 取出object
     * @return Object
     */
    public Object get(){
        if(mCls != null){
            return get(mCls,this);
        }else if(mObject != null){
            return get(mObject.getClass(),this);
        }else {
            throw new NullPointerException("cls and object cannot both be null");
        }
    }


    /**
     * 保存object中所有的值，类名作为spName, field作为key mode 默认为Context.MODE_PRIVATE;
     * @param o
     * @return boolean  true表示保存成功  false表示保存失败
     */
    public static boolean save(@NonNull Object o){
        return save(o,SpMode.MODE_PRIVATE);
    }


    /**
     * 根据对应的class文件获取对应object中所有的值
     * @param cls 获取Object所对应的class文件
     * @return object
     */
    public static Object get(Class<?> cls){
        return get(cls,SpMode.MODE_PRIVATE);
    }

    /**
     *@param spMode SP的类型
     * @param o 将要保存的object
     * @return true 表示保存成功， false表示保存失败
     */
    public static boolean save(@NonNull Object o, SpMode spMode){
        Sps sps = Sps.init().object(o).mode(spMode).build();
        return save(o,sps);
    }

    /**
     * 通过class文件找回保存的该class中所有保存的值
     * @param cls 对应实体类的class文件
     * @param spMode sp类型
     * @return object
     */
    public static Object get(Class<?> cls,SpMode spMode) {
        Sps sps = Sps.init().cls(cls).mode(spMode).build();
        return get(cls,sps);
    }


    /***
     * @param spName 定义sp名字
     * @param o 保存的实体对象
     * @param spMode 保存类型
     * @return true 保存成功   false 保存失败
     */
    public static boolean save(String spName,@NonNull Object o, SpMode spMode){
        Sps sps = Sps.init().name(spName).object(o).mode(spMode).build();
        return save(o,sps);
    }


    /**
     * @param spName sp名字
     * @param cls 对应接收类的class文件
     * @param spMode sp类型
     * @return Object 要获取的实体类对象
     */
    public static Object get(String spName,Class<?> cls,SpMode spMode){
        Sps sps = Sps.init().name(spName).cls(cls).mode(spMode).build();
        return get(cls,sps);
    }


    /**
     * 保存Boolean值
     * @param key
     * @param booleanValue
     */
    public void putBoolean(String key, boolean booleanValue) {
        mSp.edit().putBoolean(key,booleanValue).apply();
    }


    /**
     * 保存int值
     * @param key
     * @param intValue
     */
    public void putInt(String key, int intValue) {
        mSp.edit().putInt(key,intValue).apply();
    }


    /**
     * 保存string字串
     * @param key
     * @param stringValue
     */
    public void putString(String key, String stringValue) {
        mSp.edit().putString(key,stringValue).apply();
    }


    /**
     * 保存long值
     * @param key
     * @param longValue
     */
    public void putLong(String key, long longValue){
        mSp.edit().putLong(key,longValue).apply();
    }


    /**
     * 保存float值
     * @param key
     * @param floatValue
     */
    public void putFloat(String key,float floatValue){
        mSp.edit().putFloat(key,floatValue).apply();
    }


    /**
     * 保存StringSet集合
     * @param key
     * @param stringSet
     */
    public void putStringSet(String key, @NonNull Set<String> stringSet){
        mSp.edit().putStringSet(key,stringSet).apply();
    }


    /**
     * 获取Boolean值
     * @param key
     * @return boolean
     */
    public boolean getBoolean(String key){
        return mSp.getBoolean(key,false);
    }


    /**
     * 获取int值
     * @param key
     * @return int
     */
    public int getInt(String key){
        return mSp.getInt(key,-1);
    }


    /**
     * 获取string值
     * @param key
     * @return String
     */
    public String getString(String key){
        return mSp.getString(key,null);
    }


    /**
     * 获取long值
     * @param key
     * @return long
     */
    public long getLong(String key){
        return mSp.getLong(key,-1);
    }


    /**
     * 获取float值
     * @param key
     * @return float
     */
    public float getFloat(String key){
        return mSp.getFloat(key,-1);
    }


    /**
     * 获取Set<String>集合
     * @param key
     * @return Set<String>
     */
    public Set<String> getStringSet(String key){
        return mSp.getStringSet(key,null);
    }


    /**
     * 获取sp类型
     * @param spMode
     * @return int
     */
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


    /**
     *保存
     * @param o
     * @param sps
     * @return
     */
    private static boolean save(@NonNull Object o,Sps sps){
        Field[] fields = o.getClass().getDeclaredFields();
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
                }else if("float".equals(fieldType)){
                    sps.putFloat(f.getName(),f.getFloat(o));
                }else if("long".equals(fieldType)){
                    sps.putLong(f.getName(),f.getLong(o));
                }else if("java.util.Set<java.lang.String>".equals(fieldType)){
                    sps.putStringSet(f.getName(),(Set<String>) f.get(o));
                }
            } catch (IllegalAccessException e) {
                Log.e(TAG, "save: "+e.getMessage() );
                return false;
            }
        }

        Log.e(TAG, "save: success" );
        return true;
    }


    /**
     * 获取
     * @param cls
     * @param sps
     * @return Object
     */
    private static Object get(Class<?> cls,Sps sps){
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
                }else if("float".equals(fieldType)){
                    float floatValue = sps.getFloat(f.getName());
                    f.setFloat(o,floatValue);
                }else if("long".equals(fieldType)){
                    long longValue = sps.getLong(f.getName());
                    f.setLong(o,longValue);
                }else if("java.util.Set<java.lang.String>".equals(fieldType)){
                    Set<String> stringSet = sps.getStringSet(f.getName());
                    f.set(o,stringSet);
                }
            }
            Log.e(TAG, "get: "+o);
            return o;
        } catch (Exception e) {
            Log.e(TAG, "get: 没有默认的构造函数"+e.getMessage() );
            return null;
        }
    }


    /**
     * Sps构造类
     */
    public static class SpsBuilder{
        private String spName;
        private SpMode spMode;
        private Object object;
        private Class<?> cls;


        /**
         * @param spName
         * @return SpsBuilder
         */
        public SpsBuilder name(String spName){
            this.spName = spName;
            return this;
        }


        /**
         * @param spMode
         * @return SpsBuilder
         */
        public SpsBuilder mode(SpMode spMode){
            this.spMode = spMode;
            return this;
        }


        /**
         * @param o
         * @return SpsBuilder
         */
        public SpsBuilder object(Object o){
            this.object = o;
            return this;
        }


        /**
         * @param cls
         * @return SpsBuilder
         */
        public SpsBuilder cls(Class<?> cls){
            this.cls = cls;
            return this;
        }

        /**
         * 初始化 SharedPreferences 必须调用
         * @return Sps
         */
        public Sps build(){
            return new Sps(spName,object,cls,spMode);
        }
    }

}
