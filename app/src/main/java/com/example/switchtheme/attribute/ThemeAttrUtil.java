package com.example.switchtheme.attribute;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import com.example.switchtheme.ThemeApplication;
import com.example.switchtheme.ThemeManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.example.switchtheme.data.ThemeConst.ThemeAttrs;

/**
 * @author menghaonan
 * @date 2019/12/3
 */
public class ThemeAttrUtil {

    private static final Set<String> THEME_ATTR_SET = new HashSet<>(4);

    private static final int[] STYLE_THEME_ATTRS = new int[]{
            android.R.attr.textColor,
            android.R.attr.background,
            android.R.attr.src,
            android.R.attr.alpha
    };

    private static final String[] STYLE_THEME_ATTRS_NAME = new String[]{
            ThemeAttrs.THEME_ATTR_TEXT_COLOR,
            ThemeAttrs.THEME_ATTR_BACKGROUND,
            ThemeAttrs.THEME_ATTR_SRC,
            ThemeAttrs.THEME_ATTR_ALPHA
    };

    static {
        THEME_ATTR_SET.add(ThemeAttrs.THEME_ATTR_BACKGROUND);
        THEME_ATTR_SET.add(ThemeAttrs.THEME_ATTR_TEXT_COLOR);
        THEME_ATTR_SET.add(ThemeAttrs.THEME_ATTR_SRC);
        THEME_ATTR_SET.add(ThemeAttrs.THEME_ATTR_ALPHA);
    }

    public static Map<String, ThemeAttr> getThemeAttrs(AttributeSet attrSet) {
        if (attrSet == null) {
            return null;
        }
        Resources res = ThemeApplication.getContext().getResources();
        String currentTheme = ThemeManager.getInstance().getCurrentTheme();
        Map<String, ThemeAttr> attrs = new HashMap<>(4);

        // 尝试去style中取, 如果有的话
        getThemeAttrsFromStyle(attrSet, attrs);

        for (int i = 0; i < attrSet.getAttributeCount(); i++) {
            String attrName = attrSet.getAttributeName(i);
            if (!THEME_ATTR_SET.contains(attrName)) {
                continue;
            }
            String attrValue = attrSet.getAttributeValue(i);
            if (attrValue.startsWith("@")) {
                try {
                    int id = Integer.parseInt(attrValue.substring(1));
                    ThemeAttr attr = new ThemeAttr();
                    attr.setAttrName(attrName);
                    attr.setEntryName(res.getResourceEntryName(id));
                    attr.setPackageName(res.getResourcePackageName(id));
                    attr.setTypeName(res.getResourceTypeName(id));
                    attr.setResourceName(currentTheme);
                    attrs.put(attrName, attr);
                } catch (Resources.NotFoundException e) {
                    Log.e("error", "res not found" + attrName);
                }
            }
        }
        return attrs;
    }

    public static ThemeAttr getThemeAttr(@ThemeAttrs String attrName, int resId) {
        Resources res = ThemeApplication.getContext().getResources();
        ThemeAttr attr = new ThemeAttr();
        attr.setAttrName(attrName);
        attr.setEntryName(res.getResourceEntryName(resId));
        attr.setTypeName(res.getResourceTypeName(resId));
        attr.setPackageName(res.getResourcePackageName(resId));
        attr.setResourceName(ThemeManager.getInstance().getCurrentTheme());
        return attr;
    }

    private static void getThemeAttrsFromStyle(AttributeSet attrSet, Map<String, ThemeAttr> attrs) {
        for (int i = 0; i < attrSet.getAttributeCount(); i++) {
            String attrName = attrSet.getAttributeName(i);
            if (!ThemeAttrs.THEME_ATTR_STYLE.equals(attrName)) {
                continue;
            }
            // 有style属性的话, 解析里面的属性
            String attrValue = attrSet.getAttributeValue(i);
            if (!attrValue.startsWith("@")) {
                return;
            }
            int id = Integer.parseInt(attrValue.substring(1));
            TypedArray array = ThemeApplication.getContext().obtainStyledAttributes(id, STYLE_THEME_ATTRS);
            for (int j = 0; j < array.length(); j++) {
                String name = STYLE_THEME_ATTRS_NAME[j];
                int resId = array.getResourceId(j, 0);
                if (resId == 0) {
                    continue;
                }
                attrs.put(name, getThemeAttr(name, resId));
            }
            array.recycle();
        }
    }
}
