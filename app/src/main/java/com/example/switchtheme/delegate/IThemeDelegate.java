package com.example.switchtheme.delegate;

import android.util.AttributeSet;

import com.example.switchtheme.attribute.ThemeAttr;

import com.example.switchtheme.data.ThemeConst.ThemeAttrs;
import com.example.switchtheme.view.IThemeView;


/**
 * @author menghaonan
 * @date 2019/12/3
 */
public interface IThemeDelegate {

    void holdAttrs(AttributeSet attrSet, IThemeView themeView);

    ThemeAttr holdAttr(@ThemeAttrs String attrName, int resId, IThemeView themeView);

    void switchTheme(IThemeView themeView);

    void setBackground(int resId, IThemeView themeView);

    void setTextColor(int resId, IThemeView themeView);

    void setAlpha(int resId, IThemeView themeView);

    void setImageResource(int resId, IThemeView themeView);

    void register(IThemeView themeView);

    void unRegister(IThemeView themeView);
}
