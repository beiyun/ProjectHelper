package com.beiyun.library.util;


import com.beiyun.library.entity.TimeType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by beiyun on 2017/11/10.
 * 时间工具
 *
 * HH:mm:ss    15:44:40 yyyy-MM-dd    2016-08-12
 */
public class Times {


    /**
     * 获取当前时间
     * @param timeType 时间格式
     * @return 时间字符串
     */
    public static String getCurrentTime(TimeType timeType){
        return getTime(currentTimeMillis(),timeType);
    }


    /**
     * 获取时间字符串
     * @param timeMillis 时间毫秒值
     * @param timeType 时间类型
     * @return 时间字符串
     */
    public static String getTime(long timeMillis,TimeType timeType){
        Date date = new Date(timeMillis);
        return getSimpleFormat(timeType).format(date);
    }


    /**
     * 获取当前时间戳
     * @return long
     */
    public static long currentTimeMillis(){
        return System.currentTimeMillis();
    }


    /**
     * 时间格式的字符串转化为时间毫秒值
     * @param date 时间格式的字符串
     * @param timeType 时间格式
     * @return long
     */
    public static long timeMillis(String date,TimeType timeType){
        try {
            Date parse = getSimpleFormat(timeType).parse(date);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 获取当前年份
     * @return int
     */
    public static int getYear(){
        return getYear(currentTimeMillis());
    }


    /**
     * 获取指定时间戳的年份
     * @param timeMillis 时间毫秒值
     * @return int
     */
    public static int getYear(long timeMillis){
        return getCalender(timeMillis).get(Calendar.YEAR);
    }


    /**
     * 获取当前月份
     * @return int
     */
    public static int getMonth(){
        return getMonth(currentTimeMillis());
    }


    /**
     * 获取指定时间戳的月份
     * @param timeMillis 时间毫秒值
     * @return int
     */
    public static int getMonth(long timeMillis){
        return getCalender(timeMillis).get(Calendar.MONTH)+1;
    }


    /**
     * 获取当前日,即当月的第几天
     * @return int
     */
    public static int getDay(){
        return getDay(currentTimeMillis());
    }


    /**
     * 获取指定时间戳的日子
     * @param timeMillis 时间毫秒值
     * @return int
     */
    public static int getDay(long timeMillis){
        return getCalender(timeMillis).get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 获取当日是这周的第几天，星期几,中国的一周是从周一开始
     * 西方则是周日开始
     * @return int
     */
    public static int getWeekDay(){
        return getWeekDay(currentTimeMillis());
    }


    /**
     * 获取指定时间戳是当周的第几日
     * @param timeMillis 时间毫秒值
     * @return int
     */
    public static int getWeekDay(long timeMillis){
        return getCalender(timeMillis).get(Calendar.DAY_OF_WEEK)-1;
    }


    /**
     * 获取今天是星期几 eg:星期五
     * @return String
     */
    public static String getChineseWeekDay(){
        return getChineseWeekDay(currentTimeMillis());
    }


    /**
     * 获取指定时间戳是星期几
     * @param timeMillis 时间毫秒值
     * @return String
     */
    public static String getChineseWeekDay(long timeMillis){
        return getTime(timeMillis,TimeType.CN_E);
    }



    /**
     * 获取指定时间戳的Calendar对象
     * @param timeMillis 时间毫秒值
     * @return Calender
     */
    public static Calendar getCalender(long timeMillis){
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(currentTimeMillis());
        return calendar;
    }


    private static SimpleDateFormat getSimpleFormat(TimeType timeType){
        return new SimpleDateFormat(timeType.value(),Locale.CHINA);
    }
}
