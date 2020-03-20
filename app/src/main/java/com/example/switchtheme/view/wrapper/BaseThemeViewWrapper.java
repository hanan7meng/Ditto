package com.example.switchtheme.view.wrapper;

import android.content.Context;
import android.content.res.Resources;

import com.example.switchtheme.ThemeManager;
import com.example.switchtheme.attribute.ThemeAttr;

/**
 * @author menghaonan
 * @date 2020/3/20
 */
public class BaseThemeViewWrapper {

    protected Context mContext;
    protected Resources mResource;

    public BaseThemeViewWrapper(Context context) {
        mContext = context;
        mResource = context.getResources();
    }

    /**
     * resName, resType, res所在package可以确定该res的id
     *
     * @return 返回对应的id, 资源不存在返回0
     */
    protected int getRealResIdByThemeAttr(ThemeAttr attr) {
        return mResource.getIdentifier(attr.getResourceName(), attr.getTypeName(), attr.getPackageName());
    }

    /**
     * @return 返回对应的id, 资源不存在返回默认主题的
     */
    protected int getResIdByThemeAttr(ThemeAttr attr) {
        int resId = getRealResIdByThemeAttr(attr);
        // 对应主题的资源id不存在的话, 那就用默认主题的
        if (resId == 0) {
            resId = mResource.getIdentifier(attr.getEntryName(), attr.getTypeName(), attr.getPackageName());
        }
        return resId;
    }

    protected String getCurrentTheme() {
        return ThemeManager.getInstance().getCurrentTheme();
    }
}
