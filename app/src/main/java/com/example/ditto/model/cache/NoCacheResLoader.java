package com.example.ditto.model.cache;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/**
 * @author menghaonan
 * @date 2020/04/24
 */
public class NoCacheResLoader implements IDittoResLoader {

    @Override
    public Drawable getDrawable(Resources res, int resId) {
        return res.getDrawable(resId);
    }
}
