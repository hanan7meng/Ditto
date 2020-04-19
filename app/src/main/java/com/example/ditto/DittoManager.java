package com.example.ditto;

import android.text.TextUtils;

import com.example.ditto.data.DittoConst.Theme;
import com.example.ditto.data.DittoMessage;
import com.example.ditto.data.DittoPrefs;

import org.greenrobot.eventbus.EventBus;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class DittoManager {
    private String mCurrentTheme;

    private volatile static DittoManager sInstance;

    private DittoMessage mMsg;

    private DittoManager() {
        mMsg = new DittoMessage();
    }

    public static DittoManager getInstance() {
        if (sInstance == null) {
            synchronized (DittoManager.class) {
                if (sInstance == null) {
                    sInstance = new DittoManager();
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
            mCurrentTheme = DittoPrefs.getCurrentTheme();
        }
        return mCurrentTheme;
    }

    private void setCurrentTheme(@Theme String theme) {
        mCurrentTheme = theme;
        // 当前主题存本地, 下次初始化使用
        DittoPrefs.setCurrentTheme(theme);
    }

    private void sendMessage(String theme) {
        mMsg.setTheme(theme);
        EventBus.getDefault().post(mMsg);
    }
}
