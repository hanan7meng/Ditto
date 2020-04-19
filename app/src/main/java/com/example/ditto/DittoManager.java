package com.example.ditto;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.ColorRes;

import com.example.ditto.event.DittoEvent;
import com.example.ditto.event.handler.DefaultDittoEventHandler;
import com.example.ditto.event.handler.IDittoEventHandler;
import com.example.ditto.model.DittoConst.Theme;
import com.example.ditto.model.DittoPrefs;
import com.example.ditto.model.cache.IDittoResLoader;
import com.example.ditto.utils.ThemeTagUtil;
import com.example.ditto.widget.strategy.IThemeStrategy;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class DittoManager {
    private String mCurrentTheme;

    private volatile static DittoManager sInstance;

    private DittoEvent mEvent;
    private IDittoEventHandler mEventHandler;
    private IDittoResLoader mResLoader;

    private DittoManager() {
        mEvent = new DittoEvent();
        mEventHandler = new DefaultDittoEventHandler();
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
    public void switchTheme(@Theme String theme) {
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

    public void setCurrentTheme(@Theme String theme) {
        mCurrentTheme = theme;
        // 当前主题存本地, 下次初始化使用
        DittoPrefs.setCurrentTheme(theme);
    }

    private void sendMessage(String theme) {
        mEvent.setTheme(theme);
        mEventHandler.sendEvent(mEvent);
    }

    public boolean isDefaultTheme() {
        return Theme.THEME_DEFAULT.equals(mCurrentTheme);
    }

    public void setResLoader(IDittoResLoader cache) {
        mResLoader = cache;
    }

    public IDittoResLoader getResLoader() {
        return mResLoader;
    }

    public void setDittoEventHandler(IDittoEventHandler handler) {
        mEventHandler = handler;
    }

    public void setThemeStrategy(IThemeStrategy strategy, View... views) {
        ThemeTagUtil.setThemeStrategy(strategy, views);
    }

    public void setVectorColorId(@ColorRes int colorId, View... views) {
        ThemeTagUtil.setVectorColorId(colorId, views);
    }
}
