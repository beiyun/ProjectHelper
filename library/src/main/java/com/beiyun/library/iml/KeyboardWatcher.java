package com.beiyun.library.iml;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;

/**
 * Created by beiyun on 2017/11/6.
 * copy form url: https://github.com/AzimoLabs/AndroidKeyboardWatcher
 */
public class KeyboardWatcher {

    private static final String TAG = "KeyboardWatcher";
    private WeakReference<Activity> activityRef;
    private WeakReference<View> rootViewRef;
    private WeakReference<OnKeyboardToggleListener> onKeyboardToggleListenerRef;
    private ViewTreeObserver.OnGlobalLayoutListener viewTreeObserverListener;


    private static KeyboardWatcher mKeyboardWatcher;

    private KeyboardWatcher(Activity activity) {
        activityRef = new WeakReference<>(activity);
        initialize();
    }

    public static KeyboardWatcher init(Activity activity){
        if(mKeyboardWatcher == null){
            synchronized (KeyboardWatcher.class){
                if(mKeyboardWatcher == null){
                    return new KeyboardWatcher(activity);
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
        if (rootViewRef.get() != null)
            Log.e(TAG, "destroy: rootViewRef != null" );
            if (Build.VERSION.SDK_INT >= 16) {
                rootViewRef.get().getViewTreeObserver().removeOnGlobalLayoutListener(viewTreeObserverListener);
            } else {
                rootViewRef.get().getViewTreeObserver().removeGlobalOnLayoutListener(viewTreeObserverListener);
            }
    }

    private void initialize() {
        if (hasAdjustResizeInputMode()) {
            viewTreeObserverListener = new GlobalLayoutListener();
            rootViewRef = new WeakReference<>(activityRef.get().findViewById(Window.ID_ANDROID_CONTENT));
            rootViewRef.get().getViewTreeObserver().addOnGlobalLayoutListener(viewTreeObserverListener);
        } else {
            throw new IllegalArgumentException(String.format("Activity %s should have windowSoftInputMode=\"adjustResize\"" +
                    "to make KeyboardWatcher working. You can set it in AndroidManifest.xml", activityRef.get().getClass().getSimpleName()));
        }
    }

    private boolean hasAdjustResizeInputMode() {
        return (activityRef.get().getWindow().getAttributes().softInputMode & WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) != 0;
    }

    private class GlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        int initialValue;
        boolean hasSentInitialAction;
        boolean isKeyboardShown;

        @Override
        public void onGlobalLayout() {
            if (initialValue == 0) {
                initialValue = rootViewRef.get().getHeight();
            } else {
                if (initialValue > rootViewRef.get().getHeight()) {
                    if (onKeyboardToggleListenerRef.get() != null) {
                        if (!hasSentInitialAction || !isKeyboardShown) {
                            isKeyboardShown = true;
                            onKeyboardToggleListenerRef.get().onKeyboardShown(initialValue - rootViewRef.get().getHeight());
                        }
                    }
                } else {
                    if (!hasSentInitialAction || isKeyboardShown) {
                        isKeyboardShown = false;
                        rootViewRef.get().post(new Runnable() {
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
