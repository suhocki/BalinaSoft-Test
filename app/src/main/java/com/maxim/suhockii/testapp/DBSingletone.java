package com.maxim.suhockii.testapp;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * Created by hzkto on 11/10/2016.
 */

public class DBSingletone {
    private static DatabaseHelper databaseHelper = null;

    public static void init(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);

    }

    public static DatabaseHelper getHelper(){
        return databaseHelper;
    }

}
