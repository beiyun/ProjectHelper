package com.beiyun.projecthelper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.beiyun.library.entity.ViewType;
import com.beiyun.library.util.Logs;
import com.beiyun.library.util.Views;
import com.beiyun.library.view.Toast;
import com.beiyun.projecthelper.base.BaseActivity;
import com.beiyun.projecthelper.view.TestView;

import androidx.appcompat.app.ActionBar;

public class ViewsActivity extends BaseActivity {


    private EditText editText;
    private TextView textView;
    private ImageView imageView;
    private Button button;
    private RadioGroup radioGroup;
    private CheckBox checkBox;
    private RadioButton radioButton;
    private ListView listView;
    private TestView testView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("ViewsTest");
        }

        initViews();
        initData();
    }

    private void initViews() {
        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        radioButton = (RadioButton) findViewById(R.id.radioButton1);
        listView = (ListView) findViewById(R.id.listView);
        testView = (TestView) findViewById(R.id.testView);


    }

    private void initData() {
        String[] items = {"abc","def","ghi","jkl","mno","pkr","stu","vwx","yz"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.showShort(view.getClass().getSimpleName()+" position = "+ position);
            }
        });

        testView.setAutoText("autoTextView");
        testView.setMultiText("multiTextView");
        testView.setText("textView");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Logs.e(" onCreateOptionsMenu = "+menu.size());
        menu.add(1, 1, 0, "clear all");
        menu.add(1,2,0,"reset content");
        menu.add(1,3,0,"clear TextView");
        menu.add(1,4,0,"clear Button");
        menu.add(1,5,0,"clear EditText");
        menu.add(1,6,0,"clear ImageView");
        menu.add(1,7,0,"clear CheckBox");
        menu.add(1,8,0,"clear RadioGroup");
        menu.add(1,9,0,"disableControl");
        menu.add(1,10,0,"ableControl");
        menu.add(1,11,0,"clearTestView");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case 1:
               Views.clearContent(ViewType.All);
                break;
            case 2:
                resetContent();
                break;
            case 3:
                Views.clearContent(textView);
                break;
            case 4:
                Views.clearContent(button);
                break;
            case 5:
                Views.clearContent(editText);
                break;
            case 6:
                Views.clearContent(imageView);
                break;
            case 7:
                Views.clearContent(checkBox);
                break;
            case 8:
                Views.clearContent(radioGroup);
                break;
            case 9:
                Views.disableControl();
                break;
            case 10:
                Views.ableControl();
                break;
            case 11:
                Views.clearContent(testView);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void resetContent() {
        textView.setText(R.string.app_name);
        editText.setText(R.string.app_name);
        button.setText(R.string.app_name);
        imageView.setImageResource(R.mipmap.ic_launcher);
        checkBox.setChecked(true);
        radioButton.setChecked(true);
        initData();

    }


    public void imageClick(View view) {
        Toast.showShort("ImageView click");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}

