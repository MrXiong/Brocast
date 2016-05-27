package com.page.brocast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by linpg on 2016/5/27.
 */
public class AsyncReceiver extends BroadcastReceiver{
    private Activity activity;
    public AsyncReceiver(){
        super();
    }
    public AsyncReceiver(Activity activity){
        this.activity = activity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (activity instanceof ResultActivity) {
            ((ResultActivity)activity).update(intent);
        }
    }
}
