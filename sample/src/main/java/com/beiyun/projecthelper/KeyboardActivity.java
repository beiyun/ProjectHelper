package com.beiyun.projecthelper;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.beiyun.library.iml.KeyboardWatcher;
import com.beiyun.library.util.Keyboards;
import com.beiyun.library.util.Windows;
import com.beiyun.projecthelper.base.BaseActivity;

public class KeyboardActivity extends BaseActivity implements KeyboardWatcher.OnKeyboardToggleListener {

    private EditText editText;
    private static final String TAG = "KeyboardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Keyboard Test");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.toast_in,R.anim.toast_out);
            }
        });
        editText = (EditText) findViewById(R.id.editText);
        Keyboards.setOnKeyboardListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onKeyboardShown(int keyboardSize) {
        editText.setText("键盘弹起中 高度为:"+keyboardSize+"px");
        Log.e(TAG, "onKeyboardShown: "+ Windows.getActionBarHeight());

    }

    @Override
    public void onKeyboardClosed() {
        editText.setText("键盘已关闭-----");
    }

    @Override
    protected void onDestroy() {
        Keyboards.destroyKeyboardListener();
        super.onDestroy();
    }
}
