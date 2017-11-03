package com.beiyun.library.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.beiyun.library.base.Apps;

/**
 * Created by beiyun on 2017/11/3.
 * //网络工具类
 */
public class Nets {

    //获取当前的网络连接类型
    public static NetState getNetState(){
        ConnectivityManager manager = (ConnectivityManager) Apps.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI){
            return NetState.WIFI;
        }else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE){
            return NetState.MOBILE;
        }
        return NetState.NONE;
    }


    public enum NetState {
        WIFI,
        MOBILE,
        NONE
    }

}
