package com.beiyun.library.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.Stack;

/**
 * Created by beiyun on 2017/11/3.
 * 管理activity
 */
class ActivityManager {

    private static final String TAG = "ActivityManager";

    //管理所有的activity
    private Stack<Activity> mActivityStack = new Stack<>();

    private static ActivityManager manager;

    private ActivityManager(Context context){
        registerActivityLifeCallbacks(context);
    }

    protected static ActivityManager init(Context context){
        if(manager == null){
            synchronized (ActivityManager.class){
                if(manager == null){
                    return new ActivityManager(context);
                }
            }
        }

        return manager;
    }

    private void registerActivityLifeCallbacks(Context context) {

        if(context instanceof Application){
            final Application a = (Application) context;
            a.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                    pushActivity(activity);

                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    finish(activity);
                }
            });
        }

    }

    /**
     * 添加一个activity
     * @param activity
     */
    private void pushActivity(Activity activity) {
        mActivityStack.push(activity);
        Log.d(TAG, "pushActivity: "+activity.getLocalClassName());
    }


    //获取当前的activity
    protected Activity getCurrentActivity(){
        if(!mActivityStack.empty()){
            return mActivityStack.peek();
        }

        return null;
    }


    /**
     * 结束当前activity
     */
    protected void finish(){
        if(!mActivityStack.empty()){
            mActivityStack.pop();
        }
    }


    /**
     * 结束指定的activity
     * @param activity
     */
    protected void finish(Activity activity) {
        if(mActivityStack.empty()||mActivityStack.search(activity) == -1) return;
        activity.finish();
        mActivityStack.removeElement(activity);
        Log.d(TAG, "finish: "+activity.getLocalClassName());
    }


    //根据class关闭指定activity
    protected void finish(Class<? extends Activity> cls){
        if(!mActivityStack.empty()){
            Activity readyToEnd = null;
            for (Activity a:mActivityStack) {
                if(a.getClass().equals(cls)){
                    readyToEnd = a;
                }
            }

            if (readyToEnd != null) {
                readyToEnd.finish();
                mActivityStack.removeElement(readyToEnd);
            }


        }
    }


    //根据class文件获取activity
    protected Activity getActivity(Class<? extends Activity> cls){
        if(!mActivityStack.empty()){
            for (Activity a:mActivityStack) {
                if(a.getClass().equals(cls)){
                    return a;
                }
            }
        }

        return null;
    }


    //退出程序
    protected void exit(){

        if(!mActivityStack.empty()){
            synchronized (AppsIml.class){
                for (Activity next : mActivityStack) {
                    next.finish();
                }
            }
            mActivityStack.clear();
        }



    }
}
