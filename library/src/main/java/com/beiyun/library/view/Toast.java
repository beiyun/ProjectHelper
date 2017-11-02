package com.beiyun.library.view;

/**
 * Created by beiyun on 2017/11/1.
 */

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.beiyun.library.R;


/**
 * Created by beiyun on 2016/7/29.
 * Toast打印
 * 调用：
 * 1.Toast.makeText(activity,text,duration).show();//跟原生Toast控件调用方法一样
 * 2.Toast.show(activity,text);简略了，显示时长为默认LENGTH_SHORT = 2000背景为暗灰色
 * 3.Toast.error(activity,text);简略了，显示时长为默认LENGTH_SHORT = 2000,背景为暗红色
 *
 * 注意：方法Toast.makeText(activity,text,duration)的show方法有以下几种：
 * 1.show();//普通打印背景为暗灰色
 * 2.success();//背景为app主题色
 * 3.error();//背景为暗红色
 */
public class Toast {

    private static final String TAG = "Toast";

    public static final int LENGTH_SHORT = 2000;

    public static final int LENGTH_LONG = 4000;

    private WindowManager mWindowManager;

    private Handler handler = new Handler();

    private View mToastView;

    public int duration;

    public Toast() {

        if (mWindowManager != null&&mToastView != null) {
            mWindowManager.removeView(mToastView);
            mToastView = null;
            mWindowManager = null;
        }
    }

    private WindowManager.LayoutParams getWindowParams(Context context) {

        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.gravity = Gravity.TOP;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;
        params.windowAnimations = R.style.Animation_Toast;
        params.setTitle("Toast");
        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.packageName = context.getPackageName();
        return params;
    }

    public static void show(Context context,int resId){
        show(context,context.getResources().getString(resId));
    }

    public static void show(Context context,String text){
        makeText(context,text,Toast.LENGTH_SHORT).show();
    }



    public static Toast makeText(Context context, int resId, int duration){
        return makeText(context,context.getResources().getString(resId),duration);
    }

    public static Toast makeText(Context context, String text, int duration){

        Toast toast = new Toast();

        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.dialog_toast,null);
        TextView textView = (TextView) v.findViewById(R.id.toast_text);
        textView.setText(text);
        toast.duration = duration;
        toast.mToastView = v;

        return toast;
    }

    final Runnable mShow = new Runnable() {
        @Override
        public void run() {
            handleShow();

        }
    };

    final Runnable mHide = new Runnable() {
        @Override
        public void run() {
            handleHide();

        }
    };

    private void handleShow() {
        Context context = mToastView.getContext();
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams mParams = getWindowParams(context);
        mWindowManager.addView(mToastView, mParams);

        dismiss();
    }

    private void handleHide() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    handler.removeCallbacks(mHide);
                    handler.removeCallbacks(mShow);
                    handler = null;
                    if(mToastView.getParent() != null){
                        mWindowManager.removeView(mToastView);
                    }
                    mToastView = null;
                }catch (IllegalArgumentException e){
                    Log.d(TAG, "IllegalArgumentException: when removeView ");
                }
            }
        },300);

    }

    public void show(){
        handler.post(mShow);
    }



    public void dismiss(){
        handler.postDelayed(mHide,duration);
    }
}
