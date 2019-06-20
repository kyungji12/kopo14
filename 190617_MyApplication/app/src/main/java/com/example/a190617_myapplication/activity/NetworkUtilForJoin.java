package com.example.a190617_myapplication.activity;

import android.annotation.SuppressLint;
import android.os.StrictMode;

public class NetworkUtilForJoin {
    @SuppressLint("NewApi")
    static public void setNetworkPolicy() {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
