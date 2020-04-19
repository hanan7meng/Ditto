package com.example.ditto.model;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.ditto.DittoApplication;
import com.example.ditto.model.DittoConst.Theme;

/**
 * SharedPreferences操作类
 *
 * @author menghaonan
 * @date 2019/12/9
 */
public class DittoPrefs {
    private static final String PREFS_NAME_THEME = "prefs_name_current_theme";
    private static final String PREFS_KEY_CURRENT_THEME = "prefs_key_current_theme";

    private static SharedPreferences sPrefs;

    private static SharedPreferences getPreferences() {
        if (sPrefs == null) {
            sPrefs = DittoApplication.getAppContext().getSharedPreferences(PREFS_NAME_THEME, Context.MODE_PRIVATE);
        }
        return sPrefs;
    }

    public static String getCurrentTheme() {
        return getPreferences().getString(PREFS_KEY_CURRENT_THEME, Theme.THEME_DEFAULT);
    }

    public static void setCurrentTheme(@Theme String currentTheme) {
        getPreferences().edit().putString(PREFS_KEY_CURRENT_THEME, currentTheme).apply();
    }
}
