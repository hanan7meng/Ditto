package com.example.ditto.widget.strategy;

import com.example.ditto.model.attr.ThemeAttr;
import com.example.ditto.widget.view.IThemeView;

/**
 * @author menghaonan
 * @date 2020/05/01
 */
public interface IThemeStrategy {

    void setBackground(ThemeAttr attr, IThemeView themeView);

    void setTextColor(ThemeAttr attr, IThemeView themeView);

    void setAlpha(ThemeAttr attr, IThemeView themeView);

    void setImageResource(ThemeAttr attr, IThemeView themeView);

    void setHintTextColor(ThemeAttr attr, IThemeView themeView);

}
