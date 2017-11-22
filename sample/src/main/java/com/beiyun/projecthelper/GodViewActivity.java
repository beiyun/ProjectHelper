package com.beiyun.projecthelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.beiyun.library.view.GodView;

public class GodViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_god_view);
        initViews();
    }

    private void initViews() {
        GodView gv = (GodView) findViewById(R.id.godView);
        TextView tv = new TextView(this);
        tv.setText("测试godView");
        tv.setHeight(80);
        TextView tv1 = new TextView(this);
        tv.setText("测试godView1");
        tv.setHeight(80);
        gv.add(tv).add(tv1);
    }
}
