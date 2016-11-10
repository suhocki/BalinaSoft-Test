package com.maxim.suhockii.testapp;

import android.app.Application;

/**
 * Created by hzkto on 11/10/2016.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBSingletone.init(getApplicationContext());
    }
}
