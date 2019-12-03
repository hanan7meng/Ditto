package com.example.switchtheme;

import android.text.TextUtils;

import com.example.switchtheme.data.ThemeConst.Theme;
import com.example.switchtheme.data.ThemeMessage;
import com.example.switchtheme.data.ThemePrefs;

import org.greenrobot.eventbus.EventBus;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class ThemeManager {
    private String mCurrentTheme;

    private volatile static ThemeManager sInstance;

    private ThemeMessage mMsg;

    private ThemeManager() {
        mMsg = new ThemeMessage();
    }

    public static ThemeManager getInstance() {
        if (sInstance == null) {
            synchronized (ThemeManager.class) {
                if (sInstance == null) {
                    sInstance = new ThemeManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 通知View更新
     */
    void switchTheme(@Theme String theme) {
        if (TextUtils.equals(mCurrentTheme, theme)) {
            return;
        }
        setCurrentTheme(theme);
        sendMessage(theme);
    }

    public String getCurrentTheme() {
        if (TextUtils.isEmpty(mCurrentTheme)) {
            mCurrentTheme = ThemePrefs.getCurrentTheme();
        }
        return mCurrentTheme;
    }

    private void setCurrentTheme(@Theme String theme) {
        mCurrentTheme = theme;
        // 当前主题存本地, 下次初始化使用
        ThemePrefs.setCurrentTheme(theme);
    }

    private void sendMessage(String theme) {
        mMsg.setTheme(theme);
        EventBus.getDefault().post(mMsg);
    }
}
