package com.beiyun.library.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by beiyun on 2017/11/14.
 * 这是一个ViewGroup
 */
public class GodView extends LinearLayoutCompat {

    public static final int VERTICAL = 1;

    public static final int HORIZONTAL = 2;

    public GodView(Context context) {
        super(context);
        initialize(context);
    }

    public GodView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public GodView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        this.setOrientation(LinearLayoutCompat.VERTICAL);

    }

    public GodView add(View view, int orientation){
        return this;
    }


    public GodView add(LinearLayout ll, int orientation){
        return this;
    }


    public GodView add(FrameLayout fl, int orientation){
        return this;
    }


    public GodView add(RelativeLayout rl, int orientation){
        return this;
    }


    public GodView add(ListView listView,int orientation){
        return this;
    }


    public GodView add(RecyclerView rv,int orientation){
        return this;
    }


    public GodView add(ScrollView sl,int orientation){
        return this;
    }


    public GodView add(TextView tv){
        this.addView(tv,new LinearLayoutCompat.LayoutParams(-1,-2));
        return this;
    }


    public GodView add(EditText et, int orientation){
        return this;
    }


    public GodView add(Button bt, int orientation){
        return this;
    }


    public GodView add(@LayoutRes int layoutResId, int orientation){
        return this;
    }


    public GodView setEmpty(String emptyText){
        return this;
    }




}
