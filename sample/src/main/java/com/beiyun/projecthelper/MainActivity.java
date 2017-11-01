package com.beiyun.projecthelper;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.beiyun.library.util.Apps;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("test");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText("test");
        textView.setTextColor(Apps.getColor(R.color.colorAccent));



    }
}
