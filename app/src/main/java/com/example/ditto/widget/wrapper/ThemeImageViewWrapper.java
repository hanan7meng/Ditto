package com.example.ditto.widget.wrapper;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.example.ditto.model.attr.ThemeAttr;

/**
 * ImageView相关的属性操作
 *
 * @author menghaonan
 * @date 2020/3/20
 */
public class ThemeImageViewWrapper extends ThemeViewWrapper {

    public void setImageResource(ThemeAttr attr, View view) {
        if (!(view instanceof ImageView)) {
            return;
        }
        Drawable drawable = getResLoader().getDrawable(mResource, getResId(attr));
        setVectorColorIfNeeded(view, attr, drawable);
        ((ImageView) view).setImageDrawable(drawable);
    }
}
