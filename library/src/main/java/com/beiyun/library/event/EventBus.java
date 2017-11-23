package com.beiyun.library.event;


import com.beiyun.library.anot.Receiver;
import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.entity.PostType;
import com.beiyun.library.util.Logs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by beiyun on 2017/11/22.
 *
 */
public class EventBus {


    //粘性任务缓存
    private Map<String,ArrayList<Class<?>>> mStickyCaches = new ConcurrentHashMap<>();
    //任务队列
    private Map<String,Object> mTaskQueue = new ConcurrentHashMap<>();
    //receiver缓存
    private Stack<Object> mRegisterCaches = new Stack<>();

    private static EventBus mEvents;
    private EventBus(){}

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
        if(mRegisterCaches.search(receiver) != -1){
            throw new ReOrUnRegisterException("cannot reRegister or has no unregister");
        }
        mRegisterCaches.push(receiver);
        dispatchTask(receiver);
        Logs.e("register:-- receiver = "+receiver.getClass().getName());

    }



    public void dispatchTask(final Object receiver) {
        Logs.e("dispatchTask: -- receiver = "+receiver.getClass().getName());
        Method[] methods = receiver.getClass().getDeclaredMethods();
        if(methods == null || methods.length == 0) return;
        Logs.e("当前线程是 == " +Thread.currentThread().getId()+"  "+Thread.currentThread().getName());
        for (final Method m:methods) {
            Subscribe annotation = m.getAnnotation(Subscribe.class);
            if(annotation == null) continue;
            //if default type should invoke in mainThread
            if(annotation.postType() == PostType.DEFAULT){
                if(isMainThread()){
                    invokeStickyTask(m,receiver);
                }else{
                    PostHandler.postInMainThread(new PostHandler.PostCallBack() {
                        @Override
                        public void main() {
                            invokeStickyTask(m,receiver);
                        }
                    });
                }

                //invoke in mainThread
            }else if(annotation.postType() == PostType.MAIN){
                if(isMainThread()){
                    invokeMainTask(m,receiver);
                }else{
                    PostHandler.postInMainThread(new PostHandler.PostCallBack() {
                        @Override
                        public void main() {
                            invokeMainTask(m,receiver);
                        }
                    });
                }

                //invoke in Thread is not mainThread
            }else if(annotation.postType() == PostType.ASYNC){
                invokeAsyncTask(m,receiver);
            }
        }
    }


    /**
     * @return if true mean invoke in mainThread
     */
    private boolean isMainThread() {
        return Thread.currentThread().getId() == 1;
    }


    /**
     * 异步传输
     * @param method
     * @param receiver
     */
    private void invokeAsyncTask(Method method, Object receiver) {
        Logs.e("invokeAsyncTask-- method ="+method.getName()+" receiver = "+receiver.getClass().getName());
        synchronized (EventBus.class){
            try {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if(parameterTypes.length == 1 && !parameterTypes[0].getName().startsWith("android")){
                    Object poster = mTaskQueue.get(parameterTypes[0].getName());
                    if(poster == null) return;
                    method.invoke(receiver,poster);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 主线程任务
     * @param receiver 接收类
     */
    private void invokeMainTask(Method method,Object receiver) {
        Logs.e("invokeMainTask-- method ="+method.getName()+" receiver = "+receiver.getClass().getName());
        synchronized (EventBus.class){
            try {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if(parameterTypes.length == 1 && !parameterTypes[0].getName().startsWith("android")){
                    Object poster = mTaskQueue.get(parameterTypes[0].getName());
                    if(poster == null) return;
                    method.invoke(receiver,poster);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 执行粘性任务
     * @param receiver 接收类
     */
    private void invokeStickyTask(Method method,Object receiver) {
        Logs.e("invokeStickyTask -- method ="+method.getName()+" receiver = "+receiver.getClass().getName());
        synchronized (EventBus.class){
            try {
                //get parameterTypes
                Class<?>[] parameterTypes = method.getParameterTypes();
                //filter method without android method
                if(parameterTypes.length == 1 && !parameterTypes[0].getName().startsWith("android")){
                    //get key
                    String key = parameterTypes[0].getName();
                    //get poster instance
                    Object poster = mTaskQueue.get(key);
                    //get class cache by key from stickCaches
                    ArrayList<Class<?>> classes = mStickyCaches.get(key);
                    //check if has received in the class
                    if(classes != null&& classes.contains(receiver.getClass())) return;
                    //invoke method with @Subscribe(PostType = DEFAULT)
                    method.invoke(receiver,poster);
                    if(classes != null){
                        classes.add(receiver.getClass());
                    } else {
                        classes = new ArrayList<>();
                        classes.add(receiver.getClass());
                        mStickyCaches.put(key,classes);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public void post(Object poster){
        if(poster == null) return;
        String key = poster.getClass().getName();
        Logs.e("post: poster = "+poster.toString());
        mTaskQueue.put(key,poster);
        notifyPosterChanged();
        filterStickyKey(poster.getClass().getName());
    }


    //when post change, the receivers should be change
    private void notifyPosterChanged() {
        if(mRegisterCaches.empty())return;
        for (Object receiver:mRegisterCaches) {
            if(receiver != null){
                Logs.e("notifyPosterChanged:-- receiver = "+receiver.getClass().getName());
               dispatchTask(receiver);
            }
        }
    }


    /**
     * filter the sticky key in stickyCache when post
     * @param key key
     */
    private void filterStickyKey(String key) {
        if(mStickyCaches.containsKey(key)){
            mStickyCaches.remove(key);
        }
    }


    /**
     * 是否添加了注册注解
     * @param receiver receiver
     * @return true false
     */
    public boolean isInject(Object receiver){
        if(receiver == null) return false;
        Receiver annotation = receiver.getClass().getAnnotation(Receiver.class);
        Logs.e("isInject -- receiver = "+receiver.getClass().getName()+" annotation = "+ annotation);
        return annotation != null;
    }


    /**
     * 是否注册了EventBus
     * @param receiver  receiver
     * @return true false
     */
    public boolean isRegister(Object receiver){
        Logs.e("isRegister-- receiver = "+receiver.getClass().getName());
        return !mRegisterCaches.empty() && mRegisterCaches.search(receiver) != -1;
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

        Logs.e("getKeys -- receiver = "+receiver.getClass().getName() + " kes = "+ keys);

        return keys;
    }


    /**
     * 终止向下传递 终止本页所有的poster
     * @param receiver receiver
     */
    public void abort(Object receiver){
        ArrayList<String> keys = getKeys(receiver);
        Logs.e( "abort:-- receiver =  "+receiver.getClass().getName());
        if(keys == null || keys.isEmpty()) return;
        for (String key:keys) {
            Object cache = mTaskQueue.get(key);
            if(cache == null) continue;
            mTaskQueue.remove(key);
        }
    }


    /**
     * 终止某个poster向下传递
     * @param posterClass posterClass
     */
    public void abort(Class<?> posterClass){
        if(posterClass == null) return;
        Object poster = mTaskQueue.get(posterClass.getName());
        if(poster != null) mTaskQueue.remove(posterClass.getName());
    }


    /**
     * 获取Task队列
     * @return Map
     */
    public Map<String, Object> getTaskQueue() {
        return mTaskQueue;
    }


    /**
     * 接触注册
     * @param receiver receiver
     */
    public void unregister(Object receiver){
        if(receiver == null) return;
        if(!mRegisterCaches.empty() && mRegisterCaches.search(receiver) != -1){
            mRegisterCaches.removeElement(receiver);
        }
        Logs.e("unregister:-- receiver = "+receiver.getClass().getName());
    }


    /**
     * 获取最后一个加入缓存的receiver
     * @return Object
     */
    public Object getLastReceiver(){
        Logs.e("getLastReceiver:-- mRegisterCaches.size() == "+mRegisterCaches.size() );
        return mRegisterCaches.empty()? null : mRegisterCaches.peek();
    }


    /**
     * get alive receivers
     * @return Stack<Object>
     */
    public Stack<Object> getAliveReceivers(){
        return mRegisterCaches;
    }


    class ReOrUnRegisterException extends RuntimeException{
        public ReOrUnRegisterException(String message) {
            super(message);
        }
    }


}
