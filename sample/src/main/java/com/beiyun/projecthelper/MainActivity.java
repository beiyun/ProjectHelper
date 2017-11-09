package com.beiyun.projecthelper;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.beiyun.library.base.Apps;
import com.beiyun.library.util.Nets;
import com.beiyun.library.util.Sps;
import com.beiyun.library.util.Windows;
import com.beiyun.library.view.Toast;
import com.beiyun.projecthelper.base.BaseActivity;
import com.beiyun.projecthelper.adapter.MainAdapter;
import com.beiyun.projecthelper.entity.Account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements MainAdapter.OnItemClickListener {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("test");
            actionBar.setDisplayHomeAsUpEnabled(true);


        }


        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setHasFixedSize(true);


        MainAdapter  adapter = new MainAdapter(getItemData());
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        Log.e(TAG, "onCreate:getStatusBarHeight = "+ Windows.getStatusBarHeight());
        Log.e(TAG, "onCreate:getDecorViewHeight =  "+Windows.getDecorViewHeight());
        Log.e(TAG, "onCreate:getWindowHeight =  "+Windows.getWindowHeight());

    }

    private ArrayList<String> getItemData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("test App");
        data.add("test Toast");
        data.add("当前网络状态");
        data.add("exit App");
        data.add("sps.save");
        data.add("sp.get");
        data.add("Keyboards");
        return data;
    }


    @Override
    public void onItemClick(int position, String data) {
        switch (position){
            case 0:
                PackageManager manager = Apps.getPackageManager();
                Toast.makeText(MainActivity.this, "getPackageManager = "+manager, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.show(this,"测试Toast");
                break;
            case 2:
                Toast.show(this,"当前网络状态"+ Nets.getNetState());
                break;
            case 3:
                Apps.exit();
                break;
            case 4:
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
                boolean save = Sps.save(a);
                if(save){
                    Toast.show(this,"保存成功");
                }

                break;
            case 5:
                Account o = (Account) Sps.get(Account.class);
                if (o != null) {
                    Toast.makeText(this,o.toString(),Toast.LENGTH_LONG).show();
                }
                break;
            case 6:
                startActivity(new Intent(this,KeyboardActivity.class));
                overridePendingTransition(R.anim.toast_in,R.anim.toast_out);
                break;

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       menu.add("go");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}


