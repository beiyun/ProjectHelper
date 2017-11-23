package com.beiyun.projecthelper;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.entity.PostType;
import com.beiyun.projecthelper.entity.EventBusTest;

@Receiver
public class EventBusCActivity extends AppCompatActivity {

    private TextView textView;
    private static final String TAG = "EventBusCActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_c);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("C页");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        textView = (TextView) findViewById(R.id.textView);
        Log.e(TAG, "onCreate: ==========================");
    }


    @Subscribe(postType = PostType.MAIN)
    public void mainTest(String s){
        textView.setText(s);
    }

    @Subscribe
    public void receive(EventBusTest test){
        Log.e(TAG, "receive: ==========================");
        textView.setText(test.toString());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
