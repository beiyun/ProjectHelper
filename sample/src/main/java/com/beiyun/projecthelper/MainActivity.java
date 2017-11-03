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
import android.widget.Toast;

import com.beiyun.library.base.Apps;
import com.beiyun.library.util.Views;
import com.beiyun.projecthelper.Base.BaseActivity;
import com.beiyun.projecthelper.adapter.MainAdapter;

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
        data.add("test View");
        data.add("A");
        data.add("B");
        data.add("C");
        data.add("D");
        data.add("E");
        data.add("F");
        data.add("G");
        data.add("H");
        data.add("I");
        data.add("G");
        data.add("K");
        data.add("L");
        data.add("M");
        data.add("N");
        data.add("O");
        data.add("P");
        data.add("Q");
        data.add("R");
        data.add("S");
        data.add("T");
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
                com.beiyun.library.view.Toast.show(this,"测试Toast");
                break;
            case 2:
                com.beiyun.library.view.Toast.show(this,"测试View");
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


