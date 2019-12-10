package com.example.switchtheme.view;

import android.util.AttributeSet;
import android.view.View;

import com.example.switchtheme.attribute.ThemeAttr;
import com.example.switchtheme.data.ThemeMessage;

import java.util.Map;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public interface IThemeView {
    void init(AttributeSet attrSet);

    Map<String, ThemeAttr> getThemeAttrs();

    void setThemeAttrs(Map<String, ThemeAttr> attrs);

    View getView();

    void receiveThemeMessage(ThemeMessage msg);
}
