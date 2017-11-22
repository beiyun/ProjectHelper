package com.beiyun.library.util;

import com.beiyun.library.event.EventBus;

/**
 * Created by beiyun on 2017/11/17.
 * event bus
 */
public class Events {


    //禁止创建对象
    private Events(){}


    /**
     * 发送一条数据
     * @param poster 所发送的数据封装成的实例
     */
    public static void post(Object poster){
        getDefault().post(poster);
    }


    /**
     * 注册接收器
     * @param receiver 接收类 在那个类中接收就传入那个
     */
    public static void receive(Object receiver){
        getDefault().receive(receiver);
    }


    /**
     * 判断是否已经注册
     * @param receiver 接收器
     * @return true false
     */
    public static boolean isRegister(Object receiver){
       return getDefault().isRegister(receiver);
    }


    /**
     * 中断向下传输数据
     * @param receiver receiver
     */
    public static void abort(Object receiver){
        getDefault().abort(receiver);
    }


    /**
     * 中断指定poster向下传输数据
     * @param receiver receiver
     * @param posterClass posterClass
     */
    public static void abort(Object receiver,Class<?> posterClass){
        getDefault().abort(receiver,posterClass);
    }


    /**
     * 获取EventBus实例
     * @return EventBus
     */
    private static EventBus getDefault(){
        return EventBus.getDefault();
    }


    /**
     * 清空缓存
     */
    public static void clearCache(){
        getDefault().getSingleQueue().clear();
    }


}
