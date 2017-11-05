package in.coderszone.shadow.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import in.coderszone.shadow.util.L;

/**
 * Created by Vivek on 11/5/2017.
 */

public class LockUnockBroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        L.d("Got on recieve ==============> "+intent.getAction());
       // Toast.makeText(context,"Lock unlock cast reciever got intent "+intent.getAction(),Toast.LENGTH_LONG).show();
        InputMethodManager imeManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imeManager != null) {
            String id = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.DEFAULT_INPUT_METHOD
            );
            if(!id.equalsIgnoreCase("in.coderszone.shadow/.softkeyboard.SoftKeyboard")){
                Toast.makeText(context ,"You should select Shadow as your input method. The shadow app will not work to its full potential if you select others as default one", Toast.LENGTH_LONG).show();
                imeManager.showInputMethodPicker();
            }
           /* System.out.println("Current input id  "+id);
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            List<InputMethodInfo> mInputMethodProperties = imm.getEnabledInputMethodList();

            final int N = mInputMethodProperties.size();

            for (int i = 0; i < N; i++) {

                InputMethodInfo imi = mInputMethodProperties.get(i);
                System.out.println("imi.getId() =>"+imi.getId());
                *//*if (imi.getId().equals(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD))) {

                    //imi contains the information about the keyboard you are using
                    break;
                }*//*
            }*/


        } else {
            Toast.makeText(context ,"Error", Toast.LENGTH_LONG).show();
        }
    }
}