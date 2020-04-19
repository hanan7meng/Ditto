package com.example.ditto.widget.feature;

import android.util.AttributeSet;

import com.example.ditto.event.DittoEvent;
import com.example.ditto.model.DittoConst.Attrs;
import com.example.ditto.model.attr.ThemeAttrsHolder;
import com.example.ditto.utils.ThemeTagUtil;
import com.example.ditto.widget.delegate.ThemeViewDelegate;
import com.example.ditto.widget.view.IThemeView;

import org.aspectj.lang.JoinPoint;

/**
 * @author menghaonan
 * @date 2020/08/08
 */
public class ThemeFeatureManager {

    public static void setupFeature(int whichFeature, JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (!checkArgsNonNull(args)) {
            return;
        }
        IThemeView view = (IThemeView) joinPoint.getThis();

        switch (whichFeature) {
            case ThemeFeature.FEATURE_INIT:
                init(view, args);
                break;
            case ThemeFeature.FEATURE_SET_THEME_BACKGROUND:
                setThemeFeature(view, args[0], Attrs.BACKGROUND);
                break;
            case ThemeFeature.FEATURE_SET_THEME_TEXT_COLOR:
                setThemeFeature(view, args[0], Attrs.TEXT_COLOR);
                break;
            case ThemeFeature.FEATURE_SET_THEME_ALPHA:
                setThemeFeature(view, args[0], Attrs.ALPHA);
                break;
            case ThemeFeature.FEATURE_SET_THEME_HINT_TEXT_COLOR:
                setThemeFeature(view, args[0], Attrs.TEXT_COLOR_HINT);
                break;
            case ThemeFeature.FEATURE_SET_THEME_IMAGE_RES:
                setThemeFeature(view, args[0], Attrs.SRC);
                break;
            case ThemeFeature.FEATURE_REMOVE_THEME_BACKGROUND:
                removeThemeFeature(view, Attrs.BACKGROUND);
                break;
            case ThemeFeature.FEATURE_REMOVE_THEME_ALPHA:
                removeThemeFeature(view, Attrs.ALPHA);
                break;
            case ThemeFeature.FEATURE_REMOVE_THEME_TEXT_COLOR:
                removeThemeFeature(view, Attrs.TEXT_COLOR);
                break;
            case ThemeFeature.FEATURE_REMOVE_THEME_IMAGE_RES:
                removeThemeFeature(view, Attrs.SRC);
                break;
            case ThemeFeature.FEATURE_REMOVE_THEME_HINT_TEXT_COLOR:
                removeThemeFeature(view, Attrs.TEXT_COLOR_HINT);
                break;
            case ThemeFeature.FEATURE_SWITCH_THEME:
                switchTheme(view, args);
                break;
            case ThemeFeature.FEATURE_SET_VECTOR_THEME_BACKGROUND:
                setVectorThemeFeature(view, args, Attrs.BACKGROUND);
                break;
            case ThemeFeature.FEATURE_SET_VECTOR_THEME_IMAGE_RES:
                setVectorThemeFeature(view, args, Attrs.SRC);
                break;
            default:
        }
    }

    private static void init(IThemeView view, Object[] args) {
        AttributeSet attrSet = null;
        for (Object arg : args) {
            if (arg instanceof AttributeSet) {
                attrSet = (AttributeSet) arg;
            }
        }
        view.setThemeAttrsHolder(new ThemeAttrsHolder(attrSet));
        if (attrSet != null) {
            ThemeViewDelegate.getInstance().switchTheme(view);
        }
    }

    private static void setThemeFeature(IThemeView view, Object arg, String attrName) {
        Integer resId = null;
        if (arg instanceof Integer) {
            resId = (Integer) arg;
        }
        if (resId == null) {
            return;
        }
        ThemeViewDelegate.getInstance().setThemeAttr(view, view.getThemeAttrsHolder().addByResId(attrName, resId));
    }

    private static void setVectorThemeFeature(IThemeView view, Object[] args, String attrName) {
        if (args.length != 2) {
            return;
        }

        Integer resId = null;
        Integer vectorColorId = null;
        if (args[0] instanceof Integer) {
            resId = (Integer) args[0];
        }
        if (args[1] instanceof Integer) {
            vectorColorId = (Integer) args[1];
        }
        if (resId == null || vectorColorId == null) {
            return;
        }

        ThemeTagUtil.setVectorColorId(vectorColorId, view.getView());
        ThemeViewDelegate.getInstance().setThemeAttr(view, view.getThemeAttrsHolder().addByResId(attrName, resId));
    }

    private static void removeThemeFeature(IThemeView view, String attrName) {
        ThemeViewDelegate.getInstance().removeThemeAttr(view, attrName);
    }

    private static void switchTheme(IThemeView view, Object[] args) {
        Object arg = args[0];

        DittoEvent event = null;
        if (arg instanceof DittoEvent) {
            event = (DittoEvent) arg;
        }
        String customTheme = null;
        if (arg instanceof String) {
            customTheme = (String) arg;
        }

        if (customTheme != null) {
            view.setCustomTheme(customTheme);
            ThemeViewDelegate.getInstance().switchTheme(view, customTheme);
        } else {
            ThemeViewDelegate.getInstance().switchTheme(view);
        }
    }

    private static boolean checkArgsNonNull(Object[] args) {
        return args != null && args.length != 0;
    }
}
