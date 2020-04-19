package com.example.ditto.model.attr;

import androidx.annotation.IdRes;

/**
 * 主题相关属性的实体类
 *
 * @author menghaonan
 * @date 2019/12/3
 */
public class ThemeAttr {
    /**
     * 属性名, 如background
     */
    private String mAttrName;
    /**
     * 原始的(默认主题的)资源名, 如xxx_bg
     */
    private String mEntryName;
    /**
     * 资源所在的包名
     */
    private String mPackageName;
    /**
     * 资源的类型, 如drawable, color等
     */
    private String mTypeName;
    /**
     * 原始的(默认主题的)资源名和当前主题拼接后, 得到的资源名, 最终用它去取resId
     * 如xxx_bg, xxx_bg_dark, xxx_bg_night等
     */
    private String mResourceName;
    /**
     * 原始的(默认主题的)资源Id
     */
    private int mDefaultResId;

    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }

    public String getEntryName() {
        return mEntryName;
    }

    public void setEntryName(String entryName) {
        this.mEntryName = entryName;
    }

    public String getTypeName() {
        return mTypeName;
    }

    public void setTypeName(String typeName) {
        this.mTypeName = typeName;
    }

    public String getAttrName() {
        return mAttrName;
    }

    public void setAttrName(String attrName) {
        this.mAttrName = attrName;
    }

    public String getResourceName() {
        return mResourceName;
    }

    public void setTheme(String theme) {
        this.mResourceName = mEntryName.concat(theme);
    }

    public void setDefaultResId(@IdRes int resId) {
        this.mDefaultResId = resId;
    }

    public int getDefaultResId() {
        return mDefaultResId;
    }
}
