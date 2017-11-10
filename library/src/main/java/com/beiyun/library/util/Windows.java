package com.beiyun.library.util;

import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.beiyun.library.base.Apps;
import com.beiyun.library.constants.Constants;

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
        if(Constants.STATUS_BAR_HEIGHT != 0)
            return Constants.STATUS_BAR_HEIGHT;
        int dimenResId = Apps.getResource().getIdentifier("status_bar_height", "dimen", "android");
        if(dimenResId > 0){
            Constants.STATUS_BAR_HEIGHT = Apps.getResource().getDimensionPixelSize(dimenResId);
        }else{
            //下面这个方式只有viewTree发生变化的时候才会得到
            Constants.STATUS_BAR_HEIGHT = getDecorViewRect().top;
        }

        return Constants.STATUS_BAR_HEIGHT;
    }


    /**
     * 获取标题栏的高度
     * @return int actionbarHeight
     */
    public static int getActionBarHeight(){
        if(Constants.ACTION_BAR_HEIGHT != 0)
            return Constants.ACTION_BAR_HEIGHT;
        if(Apps.getCurrentActivity() instanceof AppCompatActivity){
            ActionBar actionBar = ((AppCompatActivity) Apps.getCurrentActivity()).getSupportActionBar();
            Logs.e(actionBar + "----",null);
            if(actionBar != null){
                Constants.ACTION_BAR_HEIGHT = actionBar.getHeight();
            }
        }else{
            android.app.ActionBar actionBar = Apps.getCurrentActivity().getActionBar();
            Logs.e(actionBar + "+++",null);
            if(actionBar != null){
                Constants.ACTION_BAR_HEIGHT = actionBar.getHeight();
            }
        }
        return Constants.ACTION_BAR_HEIGHT;
    }


    /**
     * 获取屏幕高度
     * @return heightPixels
     */
    public static int getWindowHeight(){
        if(Constants.WINDOW_HEIGHT == 0)
        Constants.WINDOW_HEIGHT = getWindowDisplayMetrics().heightPixels;
        return Constants.WINDOW_HEIGHT;
    }


    /**
     * 获取屏幕宽度
     * @return widthPixels
     */
    public static int getWindowWidth(){
        if(Constants.WINDOW_WIDTH == 0)
        Constants.WINDOW_WIDTH = getWindowDisplayMetrics().widthPixels;
        return Constants.WINDOW_WIDTH;
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
