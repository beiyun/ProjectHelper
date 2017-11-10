package com.beiyun.library.entity;

/**
 * _reated by beiyun on 2017/11/10.
 *  HH:mm:ss    15:44:40 yyyy-MM-dd    2016-08-12
 */
public enum TimeType {

    //英文时间字串

    HH_mm("HH:mm"),//12:23

    a_K_mm("a K:mm"),//下午 11:23

    HH_mm_ss("HH:mm:ss"), //12:23:23

    a_K_mm_ss("a K:mm:ss"), //下午 11:23:23

    yyyy_MM("yyyy-MM"), //1989-07

    yyyy_MM_dd("yyyy-MM-dd"), //1989-07-18

    yyyy_MM_dd_E("yyyy-MM-dd E"), //1989-07-18 星期五

    yyyy_MM_dd_HH_mm("yyyy-MM-dd HH:mm"), //1989-07-18 12:23

    yyyy_MM_dd_HH_mm_E("yyyy-MM-dd HH:mm E"), //1989-07-18 12:23 星期五

    yyyy_MM_dd_a_K_mm("yyyy-MM-dd a K:mm"), //1989-07-18 下午 11:23

    yyyy_MM_dd_a_K_mm_E("yyyy-MM-dd a K:mm E"), //1989-07-18 下午 11:23 星期五

    yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"), //1989-07-18 12:23:23

    yyyy_MM_dd_HH_mm_ss_E("yyyy-MM-dd HH:mm:ss E"), //1989-07-18 12:23:23 星期五

    yyyy_MM_dd_a_K_mm_ss("yyyy-MM-dd a K:mm:ss"), //1989-07-18 下午 11:23:23

    yyyy_MM_dd_a_K_mm_ss_E("yyyy-MM-dd a K:mm:ss E"), //1989-07-18 下午 11:23:23 星期五


    //中文时间字串

    CN_HH_mm("HH时mm分"),//12时23分

    CN_a_K_mm("a K时mm分"),//下午 11时23分

    CN_HH_mm_ss("HH时mm分ss秒"), //12时23分23秒

    CN_a_K_mm_ss("a K时mm分ss秒"), //下午 11时23分23秒

    CN_E("E"), //星期五

    CN_yyyy_MM("yyyy年MM月"), //1989年07月

    CN_yyyy_MM_dd("yyyy年MM月dd日"), //1989年07月18日

    CN_yyyy_MM_dd_E("yyyy年MM月dd日 E"),//1989年07月18日 星期五

    CN_yyyy_MM_dd_HH_mm("yyyy年MM月dd日 HH时mm分"),//1989年07月18日 12时23分

    CN_yyyy_MM_dd_HH_mm_E("yyyy年MM月dd日 HH时mm分 E"),//1989年07月18日 12时23分 星期五

    CN_yyyy_MM_dd_a_K_mm("yyyy年MM月dd日 a K时mm分"),//1989年07月18日 下午 11时23分

    CN_yyyy_MM_dd_a_K_mm_E("yyyy年MM月dd日 a K时mm分 E"),//1989年07月18日 下午 11时23分 星期五

    CN_yyyy_MM_dd_HH_mm_ss("yyyy年MM月dd日 HH时mm分ss秒"),//1989年07月18日 12时23分23秒

    CN_yyyy_MM_dd_HH_mm_ss_E("yyyy年MM月dd日 HH时mm分ss秒 E"),//1989年07月18日 12时23分23秒 星期五

    CN_yyyy_MM_dd_a_K_mm_ss("yyyy年MM月dd日 a K时mm分ss秒"),//1989年07月18日 下午 11时23分23秒

    CN_yyyy_MM_dd_a_K_mm_ss_E("yyyy年MM月dd日 a K时mm分ss秒 E");//1989年07月18日 下午 11时23分23秒 星期五




    private String timeType;

    TimeType(String type) {
        this.timeType = type;
    }

    @Override
    public String toString() {
        return "TimeType{" +
                "timeType='" + timeType + '\'' +
                '}';
    }

    public String value(){
        return timeType;
    }
}
