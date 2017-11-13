package com.beiyun.projecthelper;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.beiyun.library.base.ProjectHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        ProjectHelper.init(this.getContext());
        textTime();
    }

    public void textTime(){

    }


    private  void print(Object data){
        System.out.print(data);
    }
}