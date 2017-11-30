package com.beiyun.library.util;

import com.beiyun.library.event.EventBus;

/**
 * Created by beiyun on 2017/11/17.
 * 简易型eventBus
 *
 * post方式：
 * 可以在任何地方post数据
 *
 * receive方式：
 * 1.注解，在activity加上@Receiver注解,定义public方法参数是要接收的对象
 * 参数只能有一个 注解方式只能在activity中使用
 * 2.register和unregister 在你onStart()中注册，在onStop()中解除注册
 * 定义public方法参数是要接收的对象
 * 参数只能有一个 可以在任何想要接收的地方使用这种接收方式
 *
 * method定义规则：
 * 1.必须是public类型
 * 2.参数只能有一个
 * 3.参数是要接收的对象
 * 4.method需要加@Subscribe 注解
 *
 * 注解@Subscribe 说明：
 * 1.默认PostMode.SINGLE：主要用于处理粘性事件，一次发送，只要在任何receiver中定
 * 义接收该poster的方法，当该receiver启动的时候，都能够接收该poster一次，是的只接
 * 收一次，除非再次post,做了线程处理，无论在什么地方post,接收是在主线程中进行的;
 * 2.PostMode.MAIN：主要用于处理循环任务，例如progress,在任何地方post，接收都是在主线程，
 * 可以直接更新UI,post发送出去，所有注册了接收方法，并且正在活动着的receiver都可以接收到。
 * 这种方式跟PostMode.SINGLE是有明显不同的，post停止之后，新启动的receiver不会有任何响应
 * 3.PostMode.Async 异步处理循环任务，不做线程处理，在子线程post数据，当不需要在主线程接
 * 收的时候，建议使用这中方式接收，效率高。
 *
 *
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
    public static void register(Object receiver){
        getDefault().register(receiver);
    }


    /**
     * 解除注册
     * @param receiver 接收类
     */
    public static void unregister(Object receiver){
        getDefault().unregister(receiver);
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
     * 是否添加了注册注解
     * @param receiver receiver
     * @return true false
     */
    public static boolean isInject(Object receiver){
        return getDefault().isInject(receiver);
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
        getDefault().clearCache();
    }


}
