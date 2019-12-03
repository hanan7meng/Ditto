package com.example.switchtheme.data;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.switchtheme.ThemeApplication;
import com.example.switchtheme.data.ThemeConst.Theme;

/**
 * @author menghaonan
 * @date 2019/12/9
 */
public class ThemePrefs {
    private static final String PREFS_NAME_THEME = "prefs_name_current_theme";
    private static final String PREFS_KEY_CURRENT_THEME = "prefs_key_current_theme";

    private static SharedPreferences sPrefs;

    private static SharedPreferences getPreferences() {
        if (sPrefs == null) {
            sPrefs = ThemeApplication.getContext().getSharedPreferences(PREFS_NAME_THEME, Context.MODE_PRIVATE);
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
