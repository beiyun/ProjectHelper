package com.beiyun.library.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by beiyun on 2017/12/8.
 * Json操作类
 */
public class Jsons{


    public static <T>  T parseObject(String jsonStr,Class<T> clazz){
        return  JSON.parseObject(jsonStr,clazz);
    }


    public static <T> List<T> parseArray(String jsonStr, Class<T> clazz){
        return JSON.parseArray(jsonStr,clazz);
    }


    public static Object parse(String jsonStr){
        return JSON.parse(jsonStr);
    }


    public static String toJson(Object src){
        return JSON.toJSONString(src);
    }





    private Jsons(){}

    private static Jsons jsons;

    public static Jsons getJsons(){
        if(jsons == null){
            synchronized (Jsons.class){
                if(jsons == null){
                    return new Jsons();
                }
            }
        }

        return jsons;
    }


}
