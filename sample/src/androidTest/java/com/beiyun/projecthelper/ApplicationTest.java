package com.beiyun.projecthelper;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.beiyun.library.base.ProjectHelper;
import com.beiyun.library.entity.TimeType;
import com.beiyun.library.util.Times;

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
        print(Times.getCurrentTime(TimeType.HH_mm));
        print("\n"+Times.getCurrentTime(TimeType.HH_mm_ss));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd_HH_mm_ss));
        print("\n"+Times.getCurrentTime(TimeType.yyyyCMMC));
        print("\n"+Times.getCurrentTime(TimeType.yyyyCMMCddC));
        print("\n"+Times.getCurrentTime(TimeType.yyyyCMMCddCHHCmmCssC));
        print("\n"+Times.getCurrentTime(TimeType.HHCmmC));
        print("\n"+Times.getCurrentTime(TimeType.HHCmmCssC));
        print("\n"+Times.getCurrentTime(TimeType.yyyyCMMCddCHHCmmCssCE));
        print("\n"+Times.getCurrentTime(TimeType.yyyyCMMCddCE));
        print("\n"+Times.getCurrentTime(TimeType.E));
        print("\n"+Times.getChineseWeekDay());



        print("\n"+Times.timeMillis("2017年11月10日 14时52分38秒 星期五"));

    }


    private  void print(Object data){
        System.out.print(data);
    }
}