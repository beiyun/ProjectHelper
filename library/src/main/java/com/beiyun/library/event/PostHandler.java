package com.beiyun.library.event;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.beiyun.library.util.Apps;


/**
 * Created by beiyun on 2017/11/22.
 *
 */
public class PostHandler extends Handler {

    private static PostHandler mHandler;


    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if(msg.what == 0){
            PostCallBack c = (PostCallBack) msg.obj;
            if(c != null){
                c.main();
            }
        }
    }



    private PostHandler(Looper looper){
        super(looper);
    }


    private static PostHandler getHandler(){
        if(mHandler == null){
            synchronized (PostHandler.class){
                if(mHandler == null){
                    mHandler = new PostHandler(Apps.getContext().getMainLooper());
                }
            }
        }
        return mHandler;
    }


    /**
     * 将Task转到MainThread进行
     * @param callBack PostCallBack
     */
    static void postInMainThread(PostCallBack callBack){
        getHandler().obtainMessage(0,callBack).sendToTarget();

    }

    public interface PostCallBack{
        void main();
    }




}
