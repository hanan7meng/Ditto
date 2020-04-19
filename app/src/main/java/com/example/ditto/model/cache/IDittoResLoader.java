package com.example.ditto.model.cache;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

/**
 * 资源缓存
 *
 * @author menghaonan
 * @date 2020/04/22
 */
public interface IDittoResLoader {

    Drawable getDrawable(Resources res, @DrawableRes int resId);

}
