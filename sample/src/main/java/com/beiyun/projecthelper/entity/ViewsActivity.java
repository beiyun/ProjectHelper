package com.beiyun.projecthelper.entity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Views;
import com.beiyun.library.view.Toast;
import com.beiyun.projecthelper.R;
import com.beiyun.projecthelper.base.BaseActivity;

public class ViewsActivity extends BaseActivity {


    private EditText editText;
    private TextView textView;
    private ImageView imageView;
    private Button button;
    private RadioGroup radioGroup;
    private CheckBox checkBox;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("ViewsTest");
        }

        initViews();
    }

    private void initViews() {
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        radioButton = (RadioButton) findViewById(R.id.radioButton1);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void clear(View view) {
        Logs.e("clear---");
        Views.clearContent(findViewById(R.id.content));
    }

    public void clearEditText(View view) {
        Views.clearContent(editText);
    }

    public void clearTextView(View view) {
        Views.clearContent(textView);
    }

    public void clearButton(View view) {
        Views.clearContent(button);
    }


    public void clearCheckBox(View view) {
        Views.clearContent(checkBox);
    }

    public void clearRaidoGroup(View view) {
        Views.clearContent(radioGroup);
    }

    public void resetContent(View view) {
        textView.setText(R.string.app_name);
        editText.setText(R.string.app_name);
        button.setText(R.string.app_name);
        imageView.setImageResource(R.mipmap.ic_launcher);
        checkBox.setChecked(true);
        radioButton.setChecked(true);

    }

    public void clearImageView(View view) {
        Views.clearContent(imageView);
    }

    public void disableControl(View view) {
        Views.disableControl(findViewById(R.id.contentView));
    }

    public void ableControl(View view) {
        Views.ableControl();
    }

    public void imageClick(View view) {
        Toast.showShort("ImageView click");
    }
}

