package com.example.switchtheme.attribute;

import android.content.res.Resources;
import android.util.AttributeSet;

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

        for (int i = 0; i < attrSet.getAttributeCount(); i++) {
            String attrName = attrSet.getAttributeName(i);
            if (!THEME_ATTR_SET.contains(attrName)) {
                continue;
            }
            String attrValue = attrSet.getAttributeValue(i);
            if (attrValue.startsWith("@")) {
                int id = Integer.parseInt(attrValue.substring(1));
                ThemeAttr attr = new ThemeAttr();
                attr.setAttrName(attrName);
                attr.setEntryName(res.getResourceEntryName(id));
                attr.setPackageName(res.getResourcePackageName(id));
                attr.setTypeName(res.getResourceTypeName(id));
                attr.setResourceName(currentTheme);
                attrs.put(attrName, attr);
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
}
