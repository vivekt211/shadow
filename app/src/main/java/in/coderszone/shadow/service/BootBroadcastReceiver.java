package in.coderszone.shadow.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import in.coderszone.shadow.util.L;

/**
 * Created by Vivek on 10/29/2017.
 */

public class BootBroadcastReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        L.d("Got on recieve ==============> "+intent.getAction());
        //Toast.makeText(context,"1 Bootbroad cast reciever got intent ",Toast.LENGTH_LONG).show();
        Intent myIntent = new Intent(context, ContactService.class);
        context.startService(myIntent);
        //Toast.makeText(context,"Bootbroad cast reciever got intent ",Toast.LENGTH_LONG).show();
    }
}