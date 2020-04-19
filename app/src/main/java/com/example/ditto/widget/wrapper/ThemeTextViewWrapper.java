package com.example.ditto.widget.wrapper;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.ditto.model.attr.ThemeAttr;

/**
 * TextView相关的属性操作
 *
 * @author menghaonan
 * @date 2020/3/20
 */
public class ThemeTextViewWrapper extends ThemeViewWrapper {

    public void setTextColor(ThemeAttr attr, View view) {
        if (!(view instanceof TextView)) {
            return;
        }
        ColorStateList color = ContextCompat.getColorStateList(mContext, getResId(attr));
        ((TextView) view).setTextColor(color);
    }
}
