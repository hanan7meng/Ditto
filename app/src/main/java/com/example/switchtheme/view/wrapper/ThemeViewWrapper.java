package com.example.switchtheme.view.wrapper;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.switchtheme.R;
import com.example.switchtheme.attribute.ThemeAttr;
import com.example.switchtheme.attribute.ThemeAttrUtil;
import com.example.switchtheme.data.ThemeConst;

/**
 * View相关的属性操作
 *
 * @author menghaonan
 * @date 2020/3/20
 */
public class ThemeViewWrapper extends BaseThemeViewWrapper {

    public ThemeViewWrapper(Context context) {
        super(context);
    }

    public void setBackground(ThemeAttr attr, View view) {
        Drawable drawable = ContextCompat.getDrawable(mContext, getResIdByThemeAttr(attr));
        drawable = adaptVector(view, attr, drawable);
        view.setBackground(drawable);
    }

    public void setAlpha(ThemeAttr attr, View view) {
        float alpha = ResourcesCompat.getFloat(mResource, getResIdByThemeAttr(attr));
        view.setAlpha(alpha);
    }

    /**
     * 矢量图改色
     */
    protected Drawable adaptVector(View view, ThemeAttr drawableAttr, Drawable drawable) {
        if (!(drawable instanceof VectorDrawable)) {
            return drawable;
        }
        VectorDrawable vector = (VectorDrawable) drawable;

        // 查找view的tag中是否有指定的color资源, 否则找同名的color资源
        Object tag = view.getTag(R.id.id_vector_color);
        ThemeAttr attr;
        if (tag instanceof Integer) {
            int vectorColorId = (int) tag;
            attr = ThemeAttrUtil.getThemeAttr(null, vectorColorId);
        } else {
            attr = new ThemeAttr();
            attr.setTypeName(ThemeConst.ResType.TYPE_COLOR);
            attr.setPackageName(drawableAttr.getPackageName());
            attr.setEntryName(drawableAttr.getEntryName());
            attr.setResourceName(getCurrentTheme());
        }

        int colorId = getRealResIdByThemeAttr(attr);
        if (colorId == 0) {
            return drawable;
        }

        ColorStateList states = ContextCompat.getColorStateList(mContext, colorId);
        vector.setTintList(states);

        return vector;
    }
}
