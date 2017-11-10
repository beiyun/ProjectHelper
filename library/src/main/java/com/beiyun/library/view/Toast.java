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
import com.beiyun.library.base.Apps;


/**
 * Created by beiyun on 2016/7/29.
 * Toast打印
 * 调用：
 * 1.Toast.showShort(text or stringResId);简略了，显示时长为默认LENGTH_SHORT
 * 2.Toast.showLong(text or stringResId);显示时长为默认LENGTH_SHORT = 2000
 * 3.Toast.show(text or StringResId, duration);自定义显示时长 duration是毫秒值
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

    /**
     * 打印短toast
     * @param resId
     */
    public static void showShort(int resId){
        showShort(Apps.getResources().getString(resId));
    }


    /**
     * 打印短toast
     * @param text
     */
    public static void showShort(String text){
        show(text,Toast.LENGTH_SHORT);
    }


    /**
     * 打印长toast
     * @param resId
     */
    public static void showLong(int resId){
        showLong(Apps.getResources().getString(resId));
    }

    /**
     * 打印长toast
     * @param text
     */
    public static void showLong(String text){
        show(text,Toast.LENGTH_LONG);
    }


    /**
     * @param resId String资源id
     * @param duration toast x显示时长
     * @return Toast
     */
    public static void show(int resId, int duration){
        show(Apps.getResources().getString(resId),duration);
    }


    /**
     * @param text 打印的文字
     * @param duration toast显示时长
     * @return Toast
     */
    public static void show(String text, int duration){

        Toast toast = new Toast();
        LayoutInflater inflate = (LayoutInflater)
                Apps.getCurrentActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.dialog_toast,null);
        TextView textView = (TextView) v.findViewById(R.id.toast_text);
        textView.setText(text);
        toast.duration = duration;
        toast.mToastView = v;
        toast.show();

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


    /**
     * 显示toast
     */
    private void handleShow() {
        Context context = mToastView.getContext();
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams mParams = getWindowParams(context);
        mWindowManager.addView(mToastView, mParams);

        dismiss();
    }


    /**
     * 销毁toast
     */
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
