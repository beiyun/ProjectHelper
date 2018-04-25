package com.beiyun.projecthelper.base;

import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
        this.getBaseContext();
    }

    @Override
    public void onStart() {
        super.onStart();
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
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
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
