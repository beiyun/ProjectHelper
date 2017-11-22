package com.beiyun.library.event;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.beiyun.library.base.Apps;

/**
 * Created by beiyun on 2017/11/22.
 *
 */
public class PostHandler extends Handler {

    public static final int DEFAULT = 1;
    public static final int MAIN = 2;
    public static final int ASYNC = 3;

    private static PostHandler mHandler;


    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(msg.what == 0){
            notifyPosterChanged();
        }
    }

    private static final String TAG = "PostHandler";



    private void notifyPosterChanged() {
        Class receiver = EventBus.getDefault().getLastReceiver();
        if(receiver != null){
            Log.e(TAG, "notifyPosterChanged: "+receiver.getName());
            Activity activity = Apps.getActivity(receiver);
            if(activity == null) return;
            EventBus.getDefault().dispatchTask(activity);
        }
    }


    private PostHandler(Looper looper){
        super(looper);
    }


    public static PostHandler getHandler(){
        if(mHandler == null){
            synchronized (PostHandler.class){
                if(mHandler == null){
                    mHandler = new PostHandler(Apps.getContext().getMainLooper());
                }
            }
        }
        return mHandler;
    }


    public static void post(){
       getHandler().sendEmptyMessage(0);
    }




}
