package in.coderszone.shadow.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import in.coderszone.shadow.App;

public class ContactService extends Service {

    @Inject
    Context appContext;

    @Inject
    EventBus eventBus;

    BroadcastReceiver mReceiver;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        App.getAppComponent().inject(this);
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        mReceiver = new LockUnockBroadcastReciever();
        registerReceiver(mReceiver, intentFilter);


    }



    @Override
    public IBinder onBind(Intent intent) {

        //Toast.makeText(appContext,"service ONBind (((",Toast.LENGTH_LONG).show();
        return null;

    }


    @Override
    public boolean onUnbind(Intent intent) {
        //Toast.makeText(appContext,"service ON UNBind (((",Toast.LENGTH_LONG).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(appContext,"service Destroyed",Toast.LENGTH_LONG).show();
        unregisterReceiver(mReceiver);
    }
}
