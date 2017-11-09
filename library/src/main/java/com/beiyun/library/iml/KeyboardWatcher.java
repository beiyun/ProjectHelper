package com.beiyun.library.iml;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.beiyun.library.base.Apps;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Windows;

import java.lang.ref.WeakReference;

/**
 * Created by beiyun on 2017/11/6.
 *
 */
public class KeyboardWatcher {

    private static final String TAG = "KeyboardWatcher";
    private WeakReference<OnKeyboardToggleListener> onKeyboardToggleListenerRef;
    private ViewTreeObserver.OnGlobalLayoutListener viewTreeObserverListener;


    private static KeyboardWatcher mKeyboardWatcher;
    private View mDecorView;

    private KeyboardWatcher() {
        initialize();
    }

    public static KeyboardWatcher init(){
        if(mKeyboardWatcher == null){
            synchronized (KeyboardWatcher.class){
                if(mKeyboardWatcher == null){
                    return new KeyboardWatcher();
                }
            }
        }

        return mKeyboardWatcher;
    }

    public void setListener(OnKeyboardToggleListener onKeyboardToggleListener) {
        onKeyboardToggleListenerRef = new WeakReference<>(onKeyboardToggleListener);
    }


    public void destroy() {
        Log.e(TAG, "destroy: keyboard hide");

        if (mDecorView != null){
            Log.e(TAG, "destroy: rootViewRef != null" );
            if (Build.VERSION.SDK_INT >= 16) {
                mDecorView.getViewTreeObserver().removeOnGlobalLayoutListener(viewTreeObserverListener);
            } else {
                mDecorView.getViewTreeObserver().removeGlobalOnLayoutListener(viewTreeObserverListener);
            }

        }
    }

    private void initialize() {
        if (hasAdjustResizeInputMode()) {
            viewTreeObserverListener = new GlobalLayoutListener();
            mDecorView = Apps.getCurrentActivity().getWindow().getDecorView();
            mDecorView.getViewTreeObserver().addOnGlobalLayoutListener(viewTreeObserverListener);
        } else {
            throw new IllegalArgumentException(String.format("Activity %s should have windowSoftInputMode=\"adjustResize\"" +
                    "to make KeyboardWatcher working. You can set it in AndroidManifest.xml", Apps.getCurrentActivity().getClass().getSimpleName()));
        }
    }

    private boolean hasAdjustResizeInputMode() {
        return (Apps.getCurrentActivity().getWindow().getAttributes().softInputMode & WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) != 0;
    }

    private class GlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        int initialValue;
        boolean hasSentInitialAction;
        boolean isKeyboardShown;

        @Override
        public void onGlobalLayout() {
            Logs.e("initialValue = "+initialValue,null);
            if (initialValue == 0) {
                initialValue = Windows.getDecorViewHeight();
            } else {
                if (initialValue > Windows.getDecorViewHeight()) {
                    if (onKeyboardToggleListenerRef.get() != null) {
                        if (!hasSentInitialAction || !isKeyboardShown) {
                            isKeyboardShown = true;
                            onKeyboardToggleListenerRef.get().onKeyboardShown(initialValue - Windows.getDecorViewHeight());

                        }
                    }
                } else {
                    if (!hasSentInitialAction || isKeyboardShown) {
                        isKeyboardShown = false;
                        mDecorView.post(new Runnable() {
                            @Override
                            public void run() {
                                if (onKeyboardToggleListenerRef.get() != null) {
                                    onKeyboardToggleListenerRef.get().onKeyboardClosed();
                                }
                            }
                        });
                    }
                }
                hasSentInitialAction = true;
            }
        }
    }



    public interface OnKeyboardToggleListener {
        void onKeyboardShown(int keyboardSize);

        void onKeyboardClosed();
    }

}
