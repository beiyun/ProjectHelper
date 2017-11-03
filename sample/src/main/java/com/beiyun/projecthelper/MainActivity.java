package com.beiyun.projecthelper;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.beiyun.library.util.Apps;
import com.beiyun.library.util.Nets;
import com.beiyun.library.util.Sps;
import com.beiyun.library.util.Views;
import com.beiyun.library.view.Toast;
import com.beiyun.projecthelper.Base.BaseActivity;
import com.beiyun.projecthelper.adapter.MainAdapter;
import com.beiyun.projecthelper.entity.Account;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements MainAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("test");
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        Views.onCreate(this);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setHasFixedSize(true);


        MainAdapter  adapter = new MainAdapter(getItemData());
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }

    private ArrayList<String> getItemData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("test App");
        data.add("test Toast");
        data.add("当前网络状态");
        data.add("exit");
        data.add("test sps.save");
        data.add("sp.get");
        data.add("D");
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
                a.setIdentify("41022119890718481X");
                a.setName("北云");
                a.setPassword("beiyun");
                a.setPhone("18087172019");
                a.setLogin(true);
                a.setAge(26);
                Sps.save(a);
                break;
            case 5:
                Account o = (Account) Sps.get(Account.class);
                if (o != null) {
                    Toast.show(this,o.toString());
                }
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

        startActivity(new Intent(this,Main2Activity.class));
        finish();

        return true;
    }
}


