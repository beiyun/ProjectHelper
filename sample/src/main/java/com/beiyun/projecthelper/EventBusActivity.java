package com.beiyun.projecthelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.entity.PostMode;
import com.beiyun.library.util.Events;
import com.beiyun.library.view.Toast;
import com.beiyun.projecthelper.base.BaseActivity;
import com.beiyun.projecthelper.entity.Account;
import com.beiyun.projecthelper.entity.EBT;
import com.beiyun.projecthelper.entity.EBT1;
import com.beiyun.projecthelper.entity.EBT2;

import java.util.HashSet;
import java.util.Set;

import androidx.appcompat.app.ActionBar;

public class EventBusActivity extends BaseActivity implements View.OnClickListener {


    private TextView textView;
    private TextView textView1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("测试eventBus");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Events.register(this);

        Button b = (Button) findViewById(R.id.button);
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        textView = (TextView) findViewById(R.id.text);
        textView1 = (TextView) findViewById(R.id.text1);
        b.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
//        startService(new Intent(this,EventBusService.class));
    }


    @Override
    protected void onDestroy() {
        Events.unregister(this);
//        stopService(new Intent(this,EventBusService.class));
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private static final String TAG = "EventBusActivity";


    @Subscribe
    public void testEventBus(Account a) {
        Log.e(TAG, "testEventBus: "+a);
       textView.setText(a.toString());
    }


    @Subscribe(postType = PostMode.MAIN)
    public void mainTest(EBT ebt){
        textView1.setText(ebt.toString());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button://发送一条消息
                Events.post(new EBT1("发送BC的一条测试消息......2"));
                Toast.showShort("发送成功");
                break;
            case R.id.button1://本页测试
                Events.post(new EBT2("发送BC的一条测试消息....1"));
                break;
            case R.id.button2://跳转到B
                startActivity(new Intent(this,EventBusBActivity.class));
                break;
            case R.id.button3://跳转到C
                startActivity(new Intent(this,EventBusCActivity.class));
                break;
            case R.id.button4://循环测试

                Thread tr = new Thread(new Runnable() {


                    @Override
                    public void run() {
                        for (int i = 0; i < 1001; i++) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            String s = "异步position = "+i;
//                            handler.obtainMessage(0,i,0).sendToTarget();
                            Events.post(new EBT(s));

                        }





                    }
                });

                tr.start();

                break;
        }
    }


    private Account getAccount() {
        Account a = new Account();
        a.setAge(10);
        a.setIdentify("41022319900214423X");
        a.setName("北云");
        a.setPassword("beiyun");
        a.setPhone("18087172012");
        a.setLogin(true);
        a.setAge(26);
        a.setaFloat(2f);
        a.setaLong(123123123123L);
        Set<String> stringSet = new HashSet<>();
        stringSet.add("stringSet item 1");
        stringSet.add("stringSet item 2");
        stringSet.add("stringSet item 3");
        stringSet.add("stringSet item 4");
        a.setStringSet(stringSet);

        return a;
    }

}


