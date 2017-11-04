package in.coderszone.shadow.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import in.coderszone.shadow.util.L;

/**
 * Created by Vivek on 11/5/2017.
 */

public class LockUnockBroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        L.d("Got on recieve ==============> "+intent.getAction());
        Toast.makeText(context,"Lock unlock cast reciever got intent "+intent.getAction(),Toast.LENGTH_LONG).show();
    }
}