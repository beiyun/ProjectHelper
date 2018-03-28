package com.beiyun.projecthelper;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.beiyun.library.util.Windows;
import com.beiyun.projecthelper.base.BaseActivity;

public class WindowActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("WindowActivity");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String s = "状态栏高度 =" + Windows.getStatusBarHeight()
                + "  标题栏高度 =" + Windows.getActionBarHeight()
                + "  顶层View的高度 = " + Windows.getDecorViewHeight()
                + "  顶层View的宽度 = " + Windows.getDecorViewWidth()
                + "  屏幕高度 = " + Windows.getWindowHeight()
                + "  屏幕宽度 = " + Windows.getWindowWidth()
                + "  导航栏高度 = " + Windows.getNavBarHeight();

        TextView windowText = (TextView) findViewById(R.id.windowText);
        windowText.setText(s);



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    boolean isStatusBar = true;
    public void changeStatusBarColor(View view) {
        if(isStatusBar){
            Windows.setStatusBarColor(R.color.colorAccent);
            isStatusBar = false;
        }else{
            Windows.setStatusBarColor(R.color.colorPrimary);
            isStatusBar = true;
        }
    }

    boolean isNavBar = true;
    public void changeNavBarColor(View view) {
        if(isNavBar){
            Windows.setNavigationBarColor(R.color.colorAccent);
            isNavBar = false;
        }else{
            Windows.setNavigationBarColor(R.color.colorPrimary);
            isNavBar = true;
        }

    }

    boolean isActionBar = true;
    public void changeActionBarColor(View view) {
        if(isActionBar){
            Windows.setActionBarColor(R.color.colorAccent);
            Windows.setActionBarTitleTextColor(R.color.colorPrimary);
            isActionBar = false;
        }else{
            Windows.setActionBarColor(R.color.colorPrimary);
            Windows.setActionBarTitleTextColor(R.color.colorAccent);
            isActionBar = true;
        }
    }

}
