package com.beiyun.library.event;

import android.util.Log;

import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.entity.PostType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by beiyun on 2017/11/22.
 */
public class EventBus {


    private Map<String,PosterHolder> mSingleQueue = new ConcurrentHashMap<>();
    private Stack<Class<?>> mRegisterCaches = new Stack<>();
    public EventBus(){}
    private static final String TAG = "Events";
    private static EventBus mEvents;

    public static EventBus getDefault(){
        if(mEvents == null){
            synchronized (EventBus.class){
                if(mEvents == null){
                    mEvents = new EventBus();
                }
            }
        }

        return mEvents;
    }

    /**
     * 接收
     * @param receiver receiver
     */
    public void register(Object receiver){
        if(receiver == null) return;
        Log.e(TAG, "register: search = "+mRegisterCaches.search(receiver.getClass()) + mRegisterCaches.toString());
        if(mRegisterCaches.search(receiver.getClass()) != -1){
            throw new ReOrUnRegisterException("cannot reRegister or has no unregister");
        }
        mRegisterCaches.push(receiver.getClass());
        dispatchTask(receiver);
        Log.e(TAG, "registerEvents: 注册EventBus "+receiver.getClass().getName());

    }



    public void dispatchTask(Object receiver) {
        Log.e(TAG, "dispatchTask: "+receiver.getClass().getName());
        Method[] methods = receiver.getClass().getDeclaredMethods();
        if(methods == null || methods.length == 0) return;
        for (Method m:methods) {
            Subscribe annotation = m.getAnnotation(Subscribe.class);
            if(annotation == null) continue;
            if(annotation.postType() == PostType.DEFAULT){
                invokeSingleTask(m,receiver);
            }else if(annotation.postType() == PostType.MAIN){
                invokeMainTask(m,receiver);
            }else if(annotation.postType() == PostType.ASYNC){
                invokeAsyncTask(m,receiver);
            }
        }
    }


    /**
     * 异步循环传输
     * @param method
     * @param receiver
     */
    private void invokeAsyncTask(Method method, Object receiver) {

    }


    /**
     * 主线程循环任务
     * @param receiver 接收类
     */
    private void invokeMainTask(Method method,Object receiver) {

    }


    /**
     * 单次任务
     * @param receiver 接收类
     */
    private void invokeSingleTask(Method method,Object receiver) {
        try {
            Class<?>[] parameterTypes = method.getParameterTypes();
            if(parameterTypes.length == 1 && !parameterTypes[0].getName().startsWith("android")){
                PosterHolder posterHolder = mSingleQueue.get(parameterTypes[0].getName());
                if(posterHolder == null||posterHolder.getPostCaches().contains(receiver.getClass())) return;
                method.invoke(receiver,posterHolder.getPoster());
                posterHolder.addCache(receiver.getClass());
                mSingleQueue.put(parameterTypes[0].getName(),posterHolder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void post(Object poster){
        if(poster == null) return;
        String key = poster.getClass().getName();
        Log.e(TAG, "post: "+poster.getClass().getName());
        mSingleQueue.put(key,new PosterHolder(poster));
        PostHandler.post();

    }


    /**
     * 是否添加了注册注解
     * @param receiver receiver
     * @return true false
     */
    public boolean isInject(Object receiver){
        if(receiver == null) return false;
        Receiver annotation = receiver.getClass().getAnnotation(Receiver.class);
        return annotation != null;
    }


    /**
     * 是否注册了EventBus
     * @param receiver  receiver
     * @return true false
     */
    public boolean isRegister(Object receiver){
        return !mRegisterCaches.empty() && mRegisterCaches.search(receiver.getClass()) != -1;
    }


    /**
     * 获取当前接收类所有缓存的key
     * @param receiver 接收器
     * @return key list
     */
    private ArrayList<String> getKeys(Object receiver){
        if(receiver == null) return null;
        ArrayList<String> keys = new ArrayList<>();
        //make sure the method be defined with 'public' or 'protect'
        Method[] methods = receiver.getClass().getDeclaredMethods();
        if(methods == null || methods.length == 0) return null;
        for (Method m:methods) {
            Class<?>[] parameterTypes = m.getParameterTypes();
            if(parameterTypes.length == 1 && !parameterTypes[0].getName().startsWith("android")){
                keys.add(parameterTypes[0].getName());
            }
        }
        return keys;
    }


    /**
     * 终止向下传递 终止本页所有的poster
     * @param receiver receiver
     */
    public void abort(Object receiver){
        ArrayList<String> keys = getKeys(receiver);
        Log.e(TAG, "unregister: "+keys);
        if(keys == null || keys.isEmpty()) return;
        for (String key:keys) {
            Object cache = mSingleQueue.get(key);
            if(cache == null) continue;
            mSingleQueue.remove(key);
        }
    }


    /**
     * 终止某个poster向下传递
     * @param receiver receiver
     * @param posterClass posterClass
     */
    public void abort(Object receiver, Class<?> posterClass){
        ArrayList<String> keys = getKeys(receiver);
        Log.e(TAG, "unregister: "+keys);
        if(keys == null || keys.isEmpty()) return;
        for (String key:keys) {
            Object cache = mSingleQueue.get(key);
            if(cache == null || key.equals(posterClass.getName())) continue;
            mSingleQueue.remove(key);
        }
    }


    /**
     * 获取single队列
     * @return Map
     */
    public Map<String, PosterHolder> getSingleQueue() {
        return mSingleQueue;
    }


    /**
     * 接触注册
     * @param receiver receiver
     */
    public void unregister(Object receiver){
        if(receiver == null) return;
        if(!mRegisterCaches.empty() && mRegisterCaches.search(receiver.getClass()) != -1){
            mRegisterCaches.removeElement(receiver.getClass());
        }

        Log.e(TAG, "unregisterEvents: 解除注册EventBus "+receiver.getClass().getName());
    }


    /**
     * 获取最后一个加入缓存的receiver
     * @return Object
     */
    public Class<?> getLastReceiver(){
        Log.e(TAG, "getLastReceiver: size == "+mRegisterCaches.size() );
        return mRegisterCaches.empty()? null : mRegisterCaches.peek();
    }


    /**
     * 数据再次封装
     */
    static class PosterHolder{
        private ArrayList<Class> postCaches;
        private Object poster;

        public PosterHolder(Object poster) {
            this.poster = poster;
            postCaches = new ArrayList<>();
        }

        public void addCache(Class<?> clazz){
            postCaches.add(clazz);
        }

        public ArrayList<Class> getPostCaches() {
            return postCaches;
        }

        public Object getPoster() {
            return poster;
        }

        public void setPoster(Object poster) {
            this.poster = poster;
        }

        @Override
        public String toString() {
            return "PosterHolder{" +
                    "postCaches=" + postCaches +
                    ", poster=" + poster +
                    '}';
        }
    }


    class ReOrUnRegisterException extends RuntimeException{
        public ReOrUnRegisterException(String message) {
            super(message);
        }
    }


}
