package com.beiyun.library.util;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by beiyun on 2017/11/2.
 *
 */
public class Views extends AppCompatActivity{

    private Context mContext;
    private ArrayMap<String,Class> mArraymap;


    private Views(Context context){
        this.mContext = context;
        if(context instanceof AppCompatActivity){
            AppCompatActivity a = (AppCompatActivity) context;
            a.getPackageName();
        }


    }

    public static Views onCreate(Context context){
        return new Views(context);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}
