package com.beiyun.projecthelper;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.beiyun.library.anot.Subscribe;
import com.beiyun.library.entity.PostType;
import com.beiyun.library.util.Events;
import com.beiyun.library.util.Logs;

public class EventBusService extends Service {
    public EventBusService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Events.register(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }


    @Subscribe(postType = PostType.MAIN)
    public void test(String position){
        Logs.e(position);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Events.unregister(this);
    }
}
