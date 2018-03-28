package com.beiyun.projecthelper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.beiyun.library.util.Apps;
import com.beiyun.library.util.Dialogs;
import com.beiyun.library.view.Toast;
import com.beiyun.projecthelper.base.BaseActivity;

public class DialogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);



    }



    public void simpleText(View view) {
        String[] items = {"A","B","C","D"};
        Dialogs.getTextDialog()
                .setTitle("测试标题")
                .setMessage("的飞洒打飞机看来是的水电费看来就打师傅立刻就阿水电费立刻就爱上对方")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.showShort("点击确定");

                    }
                })
                .setTitleColor(Apps.getColor(R.color.colorPrimaryDark))
                .setMessageColor(Apps.getColor(R.color.colorAccent))
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.showShort("点击取消");
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .build().show();




    }
}
