package com.example.ditto.delegate;

import android.util.AttributeSet;

import com.example.ditto.attribute.ThemeAttr;
import com.example.ditto.data.DittoConst.ThemeAttrs;
import com.example.ditto.view.IThemeView;

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

    void setHintTextColor(int resId, IThemeView themeView);

    void register(IThemeView themeView);

    void unRegister(IThemeView themeView);
}
