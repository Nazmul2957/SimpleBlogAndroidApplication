package com.example.simpleblogandroidapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnectivityInfo {
    private Context context;

    public InternetConnectivityInfo(Context context){
        this.context=context;
    }
    private NetworkInfo getNetworkInfo(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(cm != null) {
            return cm.getActiveNetworkInfo();
        }else {
            return null;
        }
    }

    public boolean isConnected(){
        NetworkInfo info = getNetworkInfo();
        return (info != null && info.isConnected());
    }
}
