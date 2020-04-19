package com.example.ditto.utils;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import com.example.ditto.DittoApplication;
import com.example.ditto.DittoManager;
import com.example.ditto.model.DittoConst.Attrs;
import com.example.ditto.model.attr.ThemeAttr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Attr相关操作的工具类
 *
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
            Attrs.TEXT_COLOR,
            Attrs.BACKGROUND,
            Attrs.SRC,
            Attrs.ALPHA
    };

    static {
        THEME_ATTR_SET.add(Attrs.BACKGROUND);
        THEME_ATTR_SET.add(Attrs.TEXT_COLOR);
        THEME_ATTR_SET.add(Attrs.SRC);
        THEME_ATTR_SET.add(Attrs.ALPHA);
        THEME_ATTR_SET.add(Attrs.TEXT_COLOR_HINT);
    }

    public static Map<String, ThemeAttr> getThemeAttrs(AttributeSet attrSet) {
        if (attrSet == null) {
            return null;
        }
        Resources res = DittoApplication.getAppResources();
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
                    attr.setDefaultResId(id);
                    attr.setAttrName(attrName);
                    attr.setEntryName(res.getResourceEntryName(id));
                    attr.setPackageName(res.getResourcePackageName(id));
                    attr.setTypeName(res.getResourceTypeName(id));
                    attr.setTheme(DittoManager.getInstance().getCurrentTheme());
                    attrs.put(attrName, attr);
                } catch (Resources.NotFoundException e) {
                    Log.e("error", "res not found" + attrName);
                }
            }
        }
        return attrs;
    }

    public static ThemeAttr getThemeAttr(@Attrs String attrName, int resId) {
        Resources res = DittoApplication.getAppResources();
        ThemeAttr attr = new ThemeAttr();
        attr.setDefaultResId(resId);
        attr.setAttrName(attrName);
        attr.setEntryName(res.getResourceEntryName(resId));
        attr.setTypeName(res.getResourceTypeName(resId));
        attr.setPackageName(res.getResourcePackageName(resId));
        attr.setTheme(DittoManager.getInstance().getCurrentTheme());
        return attr;
    }

    private static void getThemeAttrsFromStyle(AttributeSet attrSet, Map<String, ThemeAttr> attrs) {
        for (int i = 0; i < attrSet.getAttributeCount(); i++) {
            String attrName = attrSet.getAttributeName(i);
            if (!Attrs.STYLE.equals(attrName)) {
                continue;
            }
            // 有style属性的话, 解析里面的属性
            String attrValue = attrSet.getAttributeValue(i);
            if (!attrValue.startsWith("@")) {
                return;
            }
            int id = Integer.parseInt(attrValue.substring(1));
            TypedArray array = DittoApplication.getAppContext().obtainStyledAttributes(id, STYLE_THEME_ATTRS);
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
