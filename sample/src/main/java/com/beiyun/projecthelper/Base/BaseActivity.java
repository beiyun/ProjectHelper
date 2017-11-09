package com.beiyun.projecthelper.base;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;

/**
 * Created by beiyun on 2017/11/2.
 */
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    ApplicationInfo info;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        info = getApplicationInfo();
        Log.d(TAG, "onCreate one para: "+toString());
        this.getBaseContext();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.d(TAG, "onUserLeaveHint: ");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown: ");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        return super.onTouchEvent(event);
    }

    @Override
    public String toString() {



        return "BaseActivity{" +
                "\ninfo.className=" + info.className +
                "\nthis.getComponentName()=" + this.getComponentName() +
                "\nthis.getLocalClassName()=" + this.getLocalClassName() +
                "\nthis.getCallingActivity()=" + this.getCallingActivity() +
                "\ninfo.name=" + info.name +
                "\ninfo.backupAgentName=" + info.backupAgentName +
                "\ninfo.dataDir=" + info.dataDir +
                "\ninfo.nativeLibraryDir=" + info.nativeLibraryDir +
                "\ninfo.processName=" + info.processName +
                "\ninfo.uid=" + info.uid +
                "\ninfo.flags=" + info.flags +
                "\ninfo.packageName=" + info.packageName +
                '}';
    }
}
