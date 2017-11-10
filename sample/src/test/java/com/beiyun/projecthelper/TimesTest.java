package com.beiyun.projecthelper;

import com.beiyun.library.entity.TimeType;
import com.beiyun.library.util.Times;

import org.junit.Test;

/**
 * Created by beiyun on 2017/11/10.
 */

public class TimesTest {

    @Test
    public void timeText(){
        print(Times.getCurrentTime(TimeType.HH_mm));
        print("\n"+Times.getCurrentTime(TimeType.a_K_mm));
        print("\n"+Times.getCurrentTime(TimeType.HH_mm_ss));
        print("\n"+Times.getCurrentTime(TimeType.a_K_mm_ss));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd_E));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd_HH_mm));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd_HH_mm_E));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd_a_K_mm_E));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd_HH_mm_ss));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd_HH_mm_ss_E));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd_a_K_mm_ss));
        print("\n"+Times.getCurrentTime(TimeType.yyyy_MM_dd_a_K_mm_ss_E));
        print("\n"+Times.getCurrentTime(TimeType.CN_E));
        print("\n"+Times.getCurrentTime(TimeType.CN_HH_mm));
        print("\n"+Times.getCurrentTime(TimeType.CN_a_K_mm));
        print("\n"+Times.getCurrentTime(TimeType.CN_HH_mm_ss));
        print("\n"+Times.getCurrentTime(TimeType.CN_a_K_mm_ss));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM_dd));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM_dd_E));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM_dd_HH_mm));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM_dd_HH_mm_E));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM_dd_a_K_mm));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM_dd_a_K_mm_E));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM_dd_HH_mm_ss));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM_dd_HH_mm_ss_E));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM_dd_a_K_mm_ss));
        print("\n"+Times.getCurrentTime(TimeType.CN_yyyy_MM_dd_a_K_mm_ss_E));


        print("\n"+Times.timeMillis("2017-11-10 23:12:20",TimeType.yyyy_MM_dd_HH_mm));
        print("\n"+Times.getTime(Times.timeMillis("2017-11-10 23:12:20",TimeType.yyyy_MM_dd_HH_mm),TimeType.yyyy_MM_dd_a_K_mm));












    }

    private  void print(Object data){
        System.out.print(data);
    }
}
