package com.beiyun.projecthelper;

import android.os.Bundle;
import android.widget.EditText;

import com.beiyun.library.listener.KeyboardWatcher;
import com.beiyun.library.util.Keyboards;
import com.beiyun.projecthelper.Base.BaseActivity;

public class Main2Activity extends BaseActivity implements KeyboardWatcher.OnKeyboardToggleListener {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = (EditText) findViewById(R.id.editText);
        Keyboards.setOnKeyBoardListener(this,this);

    }

    @Override
    public void onKeyboardShown(int keyboardSize) {
        editText.setText("键盘弹起中 高度为:"+keyboardSize);

    }

    @Override
    public void onKeyboardClosed() {
        editText.setText("键盘已关闭");
    }

    @Override
    protected void onDestroy() {
        Keyboards.destroyKeyboardListener(this);
        super.onDestroy();
    }
}
