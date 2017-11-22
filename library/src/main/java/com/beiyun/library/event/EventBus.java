package com.beiyun.library.event;

import android.util.Log;

import com.beiyun.library.anot.Receiver;
import com.beiyun.library.base.Apps;
import com.beiyun.library.entity.PostType;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by beiyun on 2017/11/22.
 */
public class EventBus {


    private Map<String,PosterHolder> mSingleQueue = new ConcurrentHashMap<>();
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
    public void receive(Object receiver) {
        if(receiver == null) return;
        Receiver annotation = receiver.getClass().getAnnotation(Receiver.class);
        if(annotation == null) return;
        if(annotation.postType() == PostType.SINGLE){
            invokeSingleTask(receiver);
        }else if(annotation.postType() == PostType.LOOP){
            invokeLoopTask(receiver);
        }


    }

    /**
     * 循环任务
     * @param receiver 接收类
     */
    private void invokeLoopTask(Object receiver) {

    }


    /**
     * 单次任务
     * @param receiver 接收类
     */
    private void invokeSingleTask(Object receiver) {
        Method[] methods = receiver.getClass().getDeclaredMethods();
        if(methods == null || methods.length == 0) return;
        for (Method m:methods) {
            try {
                Class<?>[] parameterTypes = m.getParameterTypes();
                if(parameterTypes.length == 1 && !parameterTypes[0].getName().startsWith("android")){
                    PosterHolder posterHolder = mSingleQueue.get(parameterTypes[0].getName());
                    if(posterHolder == null||posterHolder.getPostCaches().contains(receiver.getClass())) continue;
                    m.invoke(receiver,posterHolder.getPoster());
                    posterHolder.addCache(receiver.getClass());
                    mSingleQueue.put(parameterTypes[0].getName(),posterHolder);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }





    public void post(Object poster){
        if(poster == null) return;
        String key = poster.getClass().getName();
        Log.e(TAG, "post: "+poster.getClass().getName());
        mSingleQueue.put(key,new PosterHolder(poster));
        if(isRegister(Apps.getCurrentActivity())){
            receive(Apps.getCurrentActivity());
        }
    }


    /**
     * 是否已经注册接收器
     * @param receiver receiver
     */
    public boolean isRegister(Object receiver){
        if(receiver == null) return false;
        Receiver annotation = receiver.getClass().getAnnotation(Receiver.class);
        return annotation != null;
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

    public Map<String, PosterHolder> getSingleQueue() {
        return mSingleQueue;
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
}
