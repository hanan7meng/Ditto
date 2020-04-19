package com.example.ditto.view.wrapper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.example.ditto.attribute.ThemeAttr;

/**
 * ImageView相关的属性操作
 *
 * @author menghaonan
 * @date 2020/3/20
 */
public class ThemeImageViewWrapper extends ThemeViewWrapper {

    public ThemeImageViewWrapper(Context context) {
        super(context);
    }

    public void setImageResource(ThemeAttr attr, View view) {
        Drawable drawable = ContextCompat.getDrawable(mContext, getResIdByThemeAttr(attr));
        if (view instanceof ImageView) {
            drawable = adaptVector(view, attr, drawable);
            ((ImageView) view).setImageDrawable(drawable);
        }
    }
}
