package com.beiyun.projecthelper;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.beiyun.library.util.Logs;
import com.beiyun.projecthelper.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class DpsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dps);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Dps");
        }

        ImageView imageView = (ImageView) findViewById(R.id.image);
        Banner banner = (Banner) findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());

        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("http://192.168.1.140:8080/imageweb/image/ad1.png");
        imageUrls.add("http://192.168.1.140:8080/imageweb/image/ad2.png");
        imageUrls.add("http://192.168.1.140:8080/imageweb/image/ad3.png");
        imageUrls.add("http://192.168.1.140:8080/imageweb/image/ad4.png");
        banner.setImages(imageUrls);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Logs.e("OnBannerClick == "+position);
            }
        });

        Glide.with(this).load("http://img2.3lian.com/2014/f6/173/d/51.jpg")
                .into(imageView);


    }



    class GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(DpsActivity.this).load(path).into(imageView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
