package com.example.ditto.utils;

import com.example.ditto.DittoApplication;
import com.example.ditto.model.attr.ThemeAttr;
import com.example.ditto.DittoManager;

/**
 * @author menghaonan
 * @date 2020/04/24
 */
public class ThemeResIdUtil {

    /**
     * @return 返回对应主题的resId
     */
    public static int getResId(int resId) {
        // 当前是默认主题的话, 直接返回resId(要求resId始终是默认主题的！)
        if (DittoManager.getInstance().isDefaultTheme()) {
            return resId;
        }
        return getResId(ThemeAttrUtil.getThemeAttr(null, resId));
    }

    /**
     * @return 返回对应的id, 资源不存在返回默认主题的
     */
    public static int getResId(ThemeAttr attr) {
        int resId = getRealResId(attr);
        // 对应主题的资源id不存在的话, 那就用默认主题的
        if (resId == 0) {
            resId = DittoApplication.getAppResources().getIdentifier(attr.getEntryName(), attr.getTypeName(), attr.getPackageName());
        }
        return resId;
    }

    /**
     * @return 返回对应的id, 资源不存在返回0
     */
    public static int getRealResId(ThemeAttr attr) {
        return DittoApplication.getAppResources().getIdentifier(attr.getResourceName(), attr.getTypeName(), attr.getPackageName());
    }

    /**
     * @return 返回对应的id, 资源不存在返回0
     */
    public static int getRealResId(int resId) {
        return getRealResId(ThemeAttrUtil.getThemeAttr(null, resId));
    }
}
