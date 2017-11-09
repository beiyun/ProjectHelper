package com.beiyun.library.util;

import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

import com.beiyun.library.base.Apps;

/**
 * Created by beiyun on 2017/11/9.
 * decorView和window区别在于decorView会在viewTree发生变化的时候改变测量的数值，window测量是不变的
 */
public class Windows {

    /**
     * 获取状态栏高度
     * @return int px
     */
    public static int getStatusBarHeight(){
        int dimenResId = Apps.getResource().getIdentifier("status_bar_height", "dimen", "android");
        if(dimenResId > 0){
            return Apps.getResource().getDimensionPixelSize(dimenResId);
        }

        //下面这个方式只有viewTree发生变化的时候才会得到
        return getDecorViewRect().top;
    }


    /**
     * 获取标题栏的高度
     * @return int
     * note 这种方式适用于APPTheme是有actionbar的,若果用toolbar代替或者没有则测量不到
     */
    public static int getActionBarHeight(){
        View view = Apps.getCurrentActivity().getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        return view.getTop()-getStatusBarHeight();
    }


    /**
     * 获取屏幕高度
     * @return heightPixels
     */
    public static int getWindowHeight(){
        return getWindowDisplayMetrics().heightPixels;
    }


    /**
     * 获取屏幕宽度
     * @return widthPixels
     */
    public static int getWindowWidth(){
        return getWindowDisplayMetrics().widthPixels;
    }


    /**
     * 获取顶层view视图的高度
     * @return height int
     */
    public static int getDecorViewHeight(){
        return getDecorViewRect().height();
    }


    /**
     * 获取顶层view视图的宽度
     * @return width int
     */
    public static int getDecorViewWidth(){
        return getDecorViewRect().width();
    }


    /**
     * 获取屏幕参数
     * @return DisplayMetrics
     */
    public static DisplayMetrics getWindowDisplayMetrics(){
        DisplayMetrics metrics = new DisplayMetrics();
        Apps.getCurrentActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }



    /**
     * 获取顶层view视图的坐标
     * @return rect
     */
    public static Rect getDecorViewRect(){
        Rect rect = new Rect();
        Apps.getCurrentActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect;
    }



}
