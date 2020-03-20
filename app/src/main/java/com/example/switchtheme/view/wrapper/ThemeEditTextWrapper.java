package com.example.switchtheme.view.wrapper;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.example.switchtheme.attribute.ThemeAttr;

/**
 * @author menghaonan
 * @date 2020/3/20
 */
public class ThemeEditTextWrapper extends ThemeViewWrapper {

    public ThemeEditTextWrapper(Context context) {
        super(context);
    }

    public void setHintTextColor(ThemeAttr attr, View view) {
        int colorId = ContextCompat.getColor(mContext, getResIdByThemeAttr(attr));
        if (view instanceof EditText) {
            ((EditText) view).setHintTextColor(colorId);
        }
    }
}
