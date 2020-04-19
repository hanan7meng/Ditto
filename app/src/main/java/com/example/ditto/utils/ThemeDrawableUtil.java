package com.example.ditto.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

import com.example.ditto.DittoApplication;
import com.example.ditto.model.attr.ThemeAttr;

/**
 * drawable换肤相关操作的工具类
 *
 * @author menghaonan
 * @date 2020/04/21
 */
public class ThemeDrawableUtil {

    /**
     * 设置VectorDrawable对应主题的颜色
     *
     * @param colorId 该drawable默认主题的colorId
     */
    public static void setDrawableColor(@ColorRes int colorId, Drawable drawable) {
        if (!(drawable instanceof VectorDrawable)) {
            return;
        }
        setDrawableColor(ThemeAttrUtil.getThemeAttr(null, colorId), drawable);
    }

    public static void setDrawableColor(ThemeAttr attr, Drawable drawable) {
        if (!(drawable instanceof VectorDrawable)) {
            return;
        }
        int themeColorId = ThemeResIdUtil.getRealResId(attr);
        if (themeColorId == 0) {
            return;
        }
        setVectorColor(themeColorId, (VectorDrawable) drawable);
    }

    private static void setVectorColor(int colorId, VectorDrawable vector) {
        ColorStateList states = ContextCompat.getColorStateList(DittoApplication.getAppContext(), colorId);
        vector.setTintList(states);
    }
}
