package com.example.ditto.widget.delegate;

import com.example.ditto.model.DittoConst.Theme;
import com.example.ditto.model.DittoConst.Attrs;
import com.example.ditto.model.attr.ThemeAttr;
import com.example.ditto.widget.view.IThemeView;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public interface IThemeViewDelegate {

    void switchTheme(IThemeView themeView);

    void switchTheme(IThemeView themeView, @Theme String theme);

    void setThemeAttr(IThemeView themeView, ThemeAttr attr);

    void removeThemeAttr(IThemeView themeView, @Attrs String attrName);

}
