package com.example.ditto.view;

import android.util.AttributeSet;
import android.view.View;

import com.example.ditto.attribute.ThemeAttr;
import com.example.ditto.data.DittoMessage;

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

    void receiveThemeMessage(DittoMessage msg);
}
