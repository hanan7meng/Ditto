package com.example.switchtheme;

import android.app.Application;
import android.content.Context;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class ThemeApplication extends Application {
    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationContext = getApplicationContext();
    }

    public static Context getContext() {
        return sApplicationContext;
    }
}
