package com.example.switchtheme.view.wrapper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.switchtheme.attribute.ThemeAttr;

/**
 * TextView相关的属性操作
 *
 * @author menghaonan
 * @date 2020/3/20
 */
public class ThemeTextViewWrapper extends ThemeViewWrapper {

    public ThemeTextViewWrapper(Context context) {
        super(context);
    }

    public void setTextColor(ThemeAttr attr, View view) {
        ColorStateList color = ContextCompat.getColorStateList(mContext, getResIdByThemeAttr(attr));
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(color);
        }
    }
}
