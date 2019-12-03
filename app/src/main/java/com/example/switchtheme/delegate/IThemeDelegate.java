package com.example.switchtheme.delegate;

import android.util.AttributeSet;

import com.example.switchtheme.data.ThemeMessage;
import com.example.switchtheme.attribute.ThemeAttr;

import com.example.switchtheme.data.ThemeConst.ThemeAttrs;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public interface IThemeDelegate {

    void holdAttrs(AttributeSet attrSet);

    ThemeAttr holdAttr(@ThemeAttrs String attr, int resId);

    void receiveThemeMessage(ThemeMessage msg);

    void switchTheme();

    void setBackground(int resId);

    void setTextColor(int resId);

    void setAlpha(int resId);

    void setImageResource(int resId);

    void register();

    void unRegister();
}
