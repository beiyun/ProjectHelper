package com.beiyun.library.util;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

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
        int dimenResId = Apps.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(dimenResId > 0){
            Constants.STATUS_BAR_HEIGHT = Apps.getResources().getDimensionPixelSize(dimenResId);
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
     * 获取导航栏高度
     * <p>0代表不存在</p>
     *
     * @return 导航栏高度
     */
    public static int getNavBarHeight() {
        if(Constants.NAV_BAR_HEIGHT != 0)
            return Constants.NAV_BAR_HEIGHT;

        Resources res = Apps.getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
         Constants.NAV_BAR_HEIGHT =  res.getDimensionPixelSize(resourceId);
        }
        return Constants.NAV_BAR_HEIGHT;
    }

    /**
     * 隐藏导航栏
     */
    public static void hideNavBar() {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) return;
        if (getNavBarHeight() > 0) {
            View decorView = Apps.getCurrentActivity().getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
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
