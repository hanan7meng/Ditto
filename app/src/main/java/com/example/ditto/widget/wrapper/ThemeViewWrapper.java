package com.example.ditto.widget.wrapper;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import com.example.ditto.model.DittoConst;
import com.example.ditto.model.attr.ThemeAttr;
import com.example.ditto.utils.ThemeAttrUtil;
import com.example.ditto.utils.ThemeDrawableUtil;
import com.example.ditto.utils.ThemeTagUtil;

/**
 * View相关的属性操作
 *
 * @author menghaonan
 * @date 2020/3/20
 */
public class ThemeViewWrapper extends BaseThemeViewWrapper {

    public void setBackground(ThemeAttr attr, View view) {
        Drawable drawable = getResLoader().getDrawable(mResource, getResId(attr));
        setVectorColorIfNeeded(view, attr, drawable);
        view.setBackground(drawable);
    }

    public void setAlpha(ThemeAttr attr, View view) {
        float alpha = ResourcesCompat.getFloat(mResource, getResId(attr));
        view.setAlpha(alpha);
    }

    /**
     * 矢量图改色
     */
    protected void setVectorColorIfNeeded(View view, ThemeAttr drawableAttr, Drawable drawable) {
        if (!(drawable instanceof VectorDrawable)) {
            return;
        }
        ThemeAttr attr;
        // 查找view的tag中是否有指定的color资源, 否则找同名的color资源
        Integer vectorColorId = ThemeTagUtil.getVectorColorId(view);
        if (vectorColorId != null) {
            attr = ThemeAttrUtil.getThemeAttr(null, vectorColorId);
        } else {
            attr = new ThemeAttr();
            attr.setDefaultResId(drawableAttr.getDefaultResId());
            attr.setTypeName(DittoConst.ResType.COLOR);
            attr.setPackageName(drawableAttr.getPackageName());
            attr.setEntryName(drawableAttr.getEntryName());
            attr.setTheme(getCurrentTheme());
        }

        ThemeDrawableUtil.setDrawableColor(attr, drawable);
    }
}
