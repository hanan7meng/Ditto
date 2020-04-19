package com.example.ditto;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class DittoApplication extends Application {
    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return sApplicationContext;
    }

    public static Resources getAppResources() {
        return sApplicationContext.getResources();
    }
}
