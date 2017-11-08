package com.beiyun.library.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.beiyun.library.base.Apps;
import com.beiyun.library.iml.KeyboardWatcher;

/**
 * Created by beiyun on 2017/11/6.
 * 键盘操作
 */
public class Keyboards {

    /**
     *关闭键盘
     */
    public static void hideSoftInput(){

        Activity currentActivity = Apps.getCurrentActivity();
        if(currentActivity == null) return;
        View focus = currentActivity.getCurrentFocus();
        if(focus == null) return;
            InputMethodManager manager = (InputMethodManager) Apps.getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(focus.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
    }


    /**
     * 打开键盘(强制)
     */
    public static void showSoftInput(){
        Activity currentActivity = Apps.getCurrentActivity();
        if(currentActivity == null) return;
        View focus = currentActivity.getCurrentFocus();
        if(focus == null) return;
        InputMethodManager manager = (InputMethodManager) Apps.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.showSoftInput(focus,InputMethodManager.SHOW_FORCED);
    }


    /**
     * 键盘如果为打开状态则关闭，如果为关闭状态则打开
     */
    public static void toggleSoftInput(){
        InputMethodManager imm = (InputMethodManager) Apps.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }






    /**
     * 监听软键盘使用前需要在AndroidManifest.xml中
     * 当前activity配置 android:windowSoftInputMode="adjustResize"
     * @param listener 键盘监听器
     */
    public static void setOnKeyboardListener(KeyboardWatcher.OnKeyboardToggleListener listener){
        KeyboardWatcher.init(Apps.getCurrentActivity()).setListener(listener);

    }

    /**
     * 销毁软键盘监听
     */
    public static void destroyKeyboardListener(){

        KeyboardWatcher.init(Apps.getCurrentActivity()).destroy();
    }

}
