package com.beiyun.projecthelper.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.beiyun.projecthelper.R;

/**
 * Created by beiyun on 2017/12/19.
 */
public class TestView extends FrameLayout {

    private TextView textView;
    private AutoCompleteTextView autoCompleteTextView;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;

    public TestView(Context context) {
        super(context);
        init(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.layout_test_view, null);
        this.addView(inflate);
        textView = (TextView) inflate.findViewById(R.id.textView);
        autoCompleteTextView = (AutoCompleteTextView) inflate.findViewById(R.id.autoTextView);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) inflate.findViewById(R.id.mutiTextView);
    }

    public void setText(String text){
        textView.setText(text);
    }

    public void setAutoText(String autoText){
        autoCompleteTextView.setText(autoText);
    }


    public void setMultiText(String multiText){
        multiAutoCompleteTextView.setText(multiText);
    }
}
