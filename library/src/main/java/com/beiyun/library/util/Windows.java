package com.beiyun.library.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.beiyun.library.constants.WindowConstants;

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
        if(WindowConstants.STATUS_BAR_HEIGHT != 0) {
            return WindowConstants.STATUS_BAR_HEIGHT;
        }
        int dimenResId = Apps.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(dimenResId > 0){
            WindowConstants.STATUS_BAR_HEIGHT = Apps.getResources().getDimensionPixelSize(dimenResId);
        }else{
            //下面这个方式只有viewTree发生变化的时候才会得到
            WindowConstants.STATUS_BAR_HEIGHT = getDecorViewRect().top;
        }

        return WindowConstants.STATUS_BAR_HEIGHT;
    }


    /**
     * 获取标题栏的高度
     * @return int actionbarHeight
     */
    public static int getActionBarHeight(){
        if(WindowConstants.ACTION_BAR_HEIGHT != 0) {
            return WindowConstants.ACTION_BAR_HEIGHT;
        }
        if(Apps.getCurrentActivity() instanceof AppCompatActivity){
            ActionBar actionBar = ((AppCompatActivity) Apps.getCurrentActivity()).getSupportActionBar();
            Logs.e(actionBar + "----",null);
            if(actionBar != null){
                WindowConstants.ACTION_BAR_HEIGHT = actionBar.getHeight();
            }
        }else{
            android.app.ActionBar actionBar = Apps.getCurrentActivity().getActionBar();
            Logs.e(actionBar + "+++",null);
            if(actionBar != null){
                WindowConstants.ACTION_BAR_HEIGHT = actionBar.getHeight();
            }
        }
        return WindowConstants.ACTION_BAR_HEIGHT;
    }


    /**
     * 获取屏幕高度
     * @return heightPixels
     */
    public static int getWindowHeight(){
        if(WindowConstants.WINDOW_HEIGHT == 0) {
            WindowConstants.WINDOW_HEIGHT = getWindowDisplayMetrics().heightPixels;
        }
        return WindowConstants.WINDOW_HEIGHT;
    }


    /**
     * 获取屏幕宽度
     * @return widthPixels
     */
    public static int getWindowWidth(){
        if(WindowConstants.WINDOW_WIDTH == 0) {
            WindowConstants.WINDOW_WIDTH = getWindowDisplayMetrics().widthPixels;
        }
        return WindowConstants.WINDOW_WIDTH;
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
        if(WindowConstants.NAV_BAR_HEIGHT != 0) {
            return WindowConstants.NAV_BAR_HEIGHT;
        }

        Resources res = Apps.getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
         WindowConstants.NAV_BAR_HEIGHT =  res.getDimensionPixelSize(resourceId);
        }
        return WindowConstants.NAV_BAR_HEIGHT;
    }

    /**
     * 隐藏导航栏
     */
    public static void hideNavBar() {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
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


    /**
     * set statusBar color
     * @param colorResId color resources id
     */
    public static void setStatusBarColor(@ColorRes int colorResId ){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            return;
        }
        try{
            Window window = Apps.getCurrentActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Apps.getColor(colorResId));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void setNavigationBarColor(@ColorRes int colorResId){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            return;
        }
        try{
            Window window = Apps.getCurrentActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setNavigationBarColor(Apps.getColor(colorResId));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * set actonBar color if it is exist
     * @param colorResId color id
     */
    public static void setActionBarColor(@ColorRes int colorResId){
        Activity currentActivity = Apps.getCurrentActivity();
        int color = Apps.getColor(colorResId);
        ColorDrawable drawable = new ColorDrawable(color);
        if(currentActivity instanceof AppCompatActivity){
            ActionBar supportActionBar = ((AppCompatActivity) currentActivity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setBackgroundDrawable(drawable);
            }
            return;
        }

        android.app.ActionBar actionBar = currentActivity.getActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(drawable);
        }


    }


    /**
     * set actonBar color if it is exist
     * @param colorResId color id
     */
    public static void setActionBarTitleTextColor(@ColorRes int colorResId){
        Activity currentActivity = Apps.getCurrentActivity();
        int color = Apps.getColor(colorResId);
        if(currentActivity instanceof AppCompatActivity){
            ActionBar supportActionBar = ((AppCompatActivity) currentActivity).getSupportActionBar();
            if (supportActionBar != null) {
                SpannableString title = new SpannableString(supportActionBar.getTitle());
                ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
                title.setSpan(colorSpan,0,title.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                supportActionBar.setTitle(title);
            }
            return;
        }

        android.app.ActionBar actionBar = currentActivity.getActionBar();
        if (actionBar != null) {
            SpannableString title = new SpannableString(actionBar.getTitle());
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
            title.setSpan(colorSpan,0,title.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            actionBar.setTitle(title);
        }


    }





}
