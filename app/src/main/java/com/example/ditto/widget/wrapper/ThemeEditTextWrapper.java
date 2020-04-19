package com.example.ditto.widget.wrapper;

import android.view.View;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.example.ditto.model.attr.ThemeAttr;

/**
 * @author menghaonan
 * @date 2020/3/20
 */
public class ThemeEditTextWrapper extends ThemeViewWrapper {

    public void setHintTextColor(ThemeAttr attr, View view) {
        if (!(view instanceof EditText)) {
            return;
        }
        int color = ContextCompat.getColor(mContext, getResId(attr));
        ((EditText) view).setHintTextColor(color);
    }
}
