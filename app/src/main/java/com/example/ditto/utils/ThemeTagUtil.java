package com.example.ditto.utils;

import android.view.View;

import androidx.annotation.ColorRes;

import com.example.ditto.R;
import com.example.ditto.model.DittoConst.Theme;
import com.example.ditto.widget.strategy.IThemeStrategy;

/**
 * @author menghaonan
 * @date 2020/04/24
 */
public class ThemeTagUtil {

    /**
     * 设置View是否开启换肤功能
     */
    public static void setThemeEnabled(boolean enable, View view) {
        view.setTag(R.id.id_switch_theme_enabled, enable);
    }

    public static void setThemeEnabled(boolean enabled, View... views) {
        for (View view : views) {
            setThemeEnabled(enabled, view);
        }
    }

    /**
     * @return 返回View是否开启换肤功能, 默认是开启的
     */
    public static boolean isThemeEnabled(View view) {
        Object enabled = view.getTag(R.id.id_switch_theme_enabled);
        if (enabled instanceof Boolean) {
            return (boolean) enabled;
        }
        return true;
    }

    /**
     * 设置VectorDrawable的颜色值
     */
    public static void setVectorColorId(@ColorRes int colorId, View view) {
        view.setTag(R.id.id_vector_color, colorId);
    }

    public static void setVectorColorId(@ColorRes int colorId, View... views) {
        for (View view : views) {
            setVectorColorId(colorId, view);
        }
    }

    public static Integer getVectorColorId(View view) {
        Object colorId = view.getTag(R.id.id_vector_color);
        if (colorId instanceof Integer) {
            return (Integer) colorId;
        }
        return null;
    }

    /**
     * 设置View自定义的换肤策略
     */
    public static void setThemeStrategy(IThemeStrategy strategy, View view) {
        view.setTag(R.id.id_switch_theme_strategy, strategy);
    }

    public static void setThemeStrategy(IThemeStrategy strategy, View... views) {
        for (View view : views) {
            setThemeStrategy(strategy, view);
        }
    }

    public static IThemeStrategy getThemeStrategy(View view) {
        Object strategy = view.getTag(R.id.id_switch_theme_strategy);
        if (strategy instanceof IThemeStrategy) {
            return (IThemeStrategy) strategy;
        }
        return null;
    }

    /**
     * 设置View自己的Theme
     *
     * @param customTheme
     * @param view
     */
    public static void setCustomTheme(@Theme String customTheme, View view) {
        view.setTag(R.id.id_view_custom_theme, customTheme);
    }

    public static void setCustomTheme(@Theme String customTheme, View... views) {
        for (View view : views) {
            setCustomTheme(customTheme, view);
        }
    }

    public static String getCustomTheme(View view) {
        Object customTheme = view.getTag(R.id.id_view_custom_theme);
        if (customTheme instanceof String) {
            return (String) customTheme;
        }
        return null;
    }
}
