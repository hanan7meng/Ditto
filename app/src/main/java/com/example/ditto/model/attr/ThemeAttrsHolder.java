package com.example.ditto.model.attr;

import android.util.AttributeSet;

import com.example.ditto.utils.ThemeAttrUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author menghaonan
 * @date 2020/05/01
 */
public class ThemeAttrsHolder {

    private static final int DEFAULT_ATTRS_SIZE = 4;

    private Map<String, ThemeAttr> mAttrs;

    public ThemeAttrsHolder(AttributeSet attrSet) {
        mAttrs = ThemeAttrUtil.getThemeAttrs(attrSet);
    }

    public Map<String, ThemeAttr> getThemeAttrs() {
        return mAttrs;
    }

    public ThemeAttr addByResId(String attrName, int resId) {
        checkAttrsNonNull();
        ThemeAttr attr = ThemeAttrUtil.getThemeAttr(attrName, resId);
        mAttrs.put(attrName, attr);
        return attr;
    }

    private void checkAttrsNonNull() {
        if (mAttrs == null) {
            mAttrs = new HashMap<>(DEFAULT_ATTRS_SIZE);
        }
    }
}
