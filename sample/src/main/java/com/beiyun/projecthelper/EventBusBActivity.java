package com.beiyun.projecthelper;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.entity.PostMode;
import com.beiyun.library.util.Events;
import com.beiyun.projecthelper.entity.EBT;
import com.beiyun.projecthelper.entity.EBT1;

@Receiver
public class EventBusBActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_b);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("B页");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        textView = (TextView) findViewById(R.id.textView);
        textView1 = (TextView) findViewById(R.id.textView1);
    }

    @Subscribe
    public void receive(EBT1 test){
        textView.setText(test.toString());
    }

    @Subscribe(postType = PostMode.MAIN)
    public void position(EBT ebt){
        textView1.setText(ebt.toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

        Events.post(new EBT(getString(R.string.update)));
    }

}
